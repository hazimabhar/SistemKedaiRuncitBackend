package kedairuncit.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kedairuncit.backend.domain.UserEntity;
import kedairuncit.backend.domain.UserRepository;
import kedairuncit.backend.dto.UserDTO;
import kedairuncit.backend.dto.response.RegisterUserResponse;

@Service
public class UserService {

    @Autowired 
    private UserRepository userRepository;

    RegisterUserResponse response = new RegisterUserResponse();
    
    public ResponseEntity<?> registerUser(UserDTO user) {

        Optional<UserEntity> existingUser = userRepository.findByUserIcNumber(user.getUserIcNumber());

        if(existingUser.isPresent()) {
            response.setResponseMessage("GSS001");
            response.setUserId(null);
            response.setExistingIcNumber(user.getUserIcNumber());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
        }
        else{
            //hash password
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String hashedPassword = bcrypt.encode(user.getUserPassword());
            user.setUserPassword(hashedPassword);

            //insert user info
            UserEntity newUser = new UserEntity(
                user.getUserIcNumber(),
                user.getUserPassword(),
                user.getUserRole(), 
                user.getCreatedOn(),
                user.getCreatedBy(),
                user.getLastModifiedOn(), 
                user.getLastModifiedBy()
            );
            userRepository.save(newUser);
            
            response.setResponseMessage("GSS000");
            response.setUserId(newUser.getUserId());
            response.setExistingIcNumber(null);

            return ResponseEntity 
                    .status(HttpStatus.OK)
                    .body(response);
        }
    }    
}
