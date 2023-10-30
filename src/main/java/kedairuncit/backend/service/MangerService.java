package kedairuncit.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kedairuncit.backend.domain.ManagerEntity;
import kedairuncit.backend.domain.ManagerRepository;
import kedairuncit.backend.dto.ManagerDTO;

@Service
public class MangerService {

    @Autowired
    private ManagerRepository managerRepository;

    public ResponseEntity<?> registerManager(ManagerDTO manager)
    {  
        Optional<ManagerEntity> existingEmail = managerRepository.findByManagerEmail(manager.getManagerEmail());  
        
        if(existingEmail.isPresent()){
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("GSS003");
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

            return ResponseEntity
            .status(HttpStatus.OK)
            .body("GSS002");
        }

    }
}
