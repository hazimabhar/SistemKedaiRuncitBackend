package kedairuncit.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kedairuncit.backend.domain.ManagerEntity;
import kedairuncit.backend.domain.ManagerRepository;
import kedairuncit.backend.domain.UserEntity;
import kedairuncit.backend.dto.ManagerDTO;
import kedairuncit.backend.dto.response.RegisterManagerResponse;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    RegisterManagerResponse response = new RegisterManagerResponse();

    public ResponseEntity<?> registerManager(ManagerDTO manager)
    {  
        Optional<ManagerEntity> existingEmail = managerRepository.findByManagerEmail(manager.getManagerEmail());  
        
        if(existingEmail.isPresent()){

            response.setResponseMessage("GSS004");
            response.setExistingEmail(manager.getManagerEmail());

            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
        }
        else{
            ManagerEntity newManager = new ManagerEntity(
                manager.getManagerName(),
                manager.getManagerAddress(),
                manager.getManagerPhoneNumber(),
                manager.getManagerGender(),
                manager.getManagerEmail(),
                manager.getCreatedOn(),
                manager.getCreatedBy(),
                manager.getLastModifiedOn(),
                manager.getLastModifiedBy(),
                manager.getUserId()
            );

            managerRepository.save(newManager);

            response.setResponseMessage("GSS002");
            response.setExistingEmail(null);
            return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
        }
    }
}
