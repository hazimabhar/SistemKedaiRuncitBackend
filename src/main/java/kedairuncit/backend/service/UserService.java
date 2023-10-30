package kedairuncit.backend.service;

import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import kedairuncit.backend.domain.UserEntity;
import kedairuncit.backend.domain.UserRepository;
import kedairuncit.backend.dto.UserDTO;

@Service
public class UserService {

    @Autowired 
    private UserRepository userRepository;

    
    public ResponseEntity<?> registerUser(UserDTO user) {

        Optional<UserEntity> existingUser = userRepository.findByUserIcNumber(user.getUserIcNumber());

        if(existingUser.isPresent()) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("GSS001");
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

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("GSS000");
        }
    }    
}
