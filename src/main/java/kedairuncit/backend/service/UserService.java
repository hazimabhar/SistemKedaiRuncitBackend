package kedairuncit.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kedairuncit.backend.domain.UserEntity;
import kedairuncit.backend.domain.UserRepository;
import kedairuncit.backend.dto.UserDTO;

@Service
public class UserService {

    @Autowired

    private UserRepository userRepository;

    public ResponseEntity<?> registerUser(UserEntity user) {

        Optional<UserEntity> existingUser = userRepository.findByUserIcNumber(user.getUserIcNumber());

        if(existingUser.isPresent()) {
        
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("GSS001");
        }

        else{
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

            if(user.getUserRole().equals("Manager")){

            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("GSS000");
        }
    }


    
}
