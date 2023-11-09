package kedairuncit.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kedairuncit.backend.domain.ManagerEntity;
import kedairuncit.backend.domain.ManagerRepository;
import kedairuncit.backend.domain.UserEntity;
import kedairuncit.backend.domain.UserRepository;
import kedairuncit.backend.domain.WorkerEntity;
import kedairuncit.backend.domain.WorkerRepository;
import kedairuncit.backend.dto.ResetPasswordDTO;
import kedairuncit.backend.dto.UserDTO;
import kedairuncit.backend.dto.response.AuthenticateUserResponse;
import kedairuncit.backend.dto.response.RegisterUserResponse;

@Service
public class UserService {

    @Autowired 
    private UserRepository userRepository;
    private ManagerRepository managerRepository;
    private WorkerRepository workerRepository;

    private String successRegister = "GSS000";
    private String existingIcNumber = "GSS001";
    private String loginSuccees = "GSS005";
    private String icNumberNotExist = "GSS006";
    private String wrongPassword = "GSS007";
    private String wrongPassword3 = "GSS008";
    private String ableToReset = "GSS009";
    private String unableToReset = "GSS010";


    public UserService(UserRepository userRepository, ManagerRepository managerRepository, WorkerRepository workerRepository) {

        this.userRepository = userRepository;
        this.managerRepository = managerRepository;
        this.workerRepository = workerRepository;
    }

    RegisterUserResponse response = new RegisterUserResponse();
    AuthenticateUserResponse loginResponse = new AuthenticateUserResponse();
    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        
    int loginAttempt =0;
     
    public ResponseEntity<?> registerUser(UserDTO user) {

        Optional<UserEntity> existingUser = userRepository.findByUserIcNumber(user.getUserIcNumber());

        if(existingUser.isPresent()) {
            response.setResponseMessage(existingIcNumber);
            response.setUserId(null);
            response.setExistingIcNumber(user.getUserIcNumber());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
        }
        else{
            //hash password
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
            
            response.setResponseMessage(successRegister);
            response.setUserId(newUser.getUserId());
            response.setExistingIcNumber(null);

            return ResponseEntity 
                    .status(HttpStatus.OK)
                    .body(response);
        }
    }
    
    public ResponseEntity<?> authenticateUser(UserDTO user){
        Optional<UserEntity> existingUser = userRepository.findByUserIcNumber(user.getUserIcNumber());

        if(existingUser.isEmpty()){
            return icNumberNotExist();
        }
        else{
            boolean isAuthenticated = bcrypt.matches(user.getUserPassword(), existingUser.get().getUserPassword());

            if(!isAuthenticated) {

                loginAttempt++;
                    
                if(loginAttempt < 4){
                    loginResponse.setResponseMessage(wrongPassword);
                    loginResponse.setUserRole(null);

                    return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(loginResponse);
                }
                else{
                    loginResponse.setResponseMessage(wrongPassword3);
                    loginResponse.setUserRole(null);

                    return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(loginResponse);  
                }
            }
            else{

                loginAttempt = 0;
                loginResponse.setResponseMessage(loginSuccees);
                loginResponse.setUserRole(existingUser.get().getUserRole());

                return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(loginResponse);
            }
        }
    }
    public ResponseEntity<?> resetPasswordCredentials(ResetPasswordDTO user){

        loginAttempt = 0;
        Optional<UserEntity> existingUser = userRepository.findByUserIcNumber(user.getUserIcNumber());

        if(existingUser.isEmpty())
        {
            return icNumberNotExist();
        }
        else if(existingUser.get().getUserRole().equals("Manager")){

            Optional<ManagerEntity> managerData = managerRepository.findByUserId(existingUser.get().getUserId());

            if(managerData.isEmpty())
            {
                return unableToReset(existingUser.get());
            }
            else
            {
                if(user.getUserEmail().equals(managerData.get().getManagerEmail()) && user.getUserPhoneNumber().equals(managerData.get().getManagerPhoneNumber()))
                {
                   return ableToReset(existingUser.get());
                }
                else{
                    return unableToReset(existingUser.get());
                }
            }
        }
        else {
            Optional<WorkerEntity> workerData = workerRepository.findByUserId(existingUser.get().getUserId());

            if(workerData.isEmpty()){
                return unableToReset(existingUser.get());
            }
            else{
                if(user.getUserEmail().equals(workerData.get().getWorkerEmail()) && user.getUserPhoneNumber().equals(workerData.get().getWorkerPhoneNumber()))
                {
                   return ableToReset(existingUser.get());
                }
                else{
                    return unableToReset(existingUser.get());
                }
            }
        }
    }

    public ResponseEntity<?> unableToReset(UserEntity existingUser){
        loginResponse.setResponseMessage(unableToReset);
        loginResponse.setUserRole(existingUser.getUserRole());
        
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(loginResponse);
    }
    public ResponseEntity<?> ableToReset(UserEntity existingUser){
        loginResponse.setResponseMessage(ableToReset);
        loginResponse.setUserRole(existingUser.getUserRole());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(loginResponse);
    }
    public ResponseEntity<?> icNumberNotExist(){
            loginResponse.setResponseMessage(icNumberNotExist);
            loginResponse.setUserRole(null);

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(loginResponse);
    }
}
