package kedairuncit.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kedairuncit.backend.domain.WorkerEntity;
import kedairuncit.backend.domain.WorkerRepository;
import kedairuncit.backend.dto.WorkerDTO;
import kedairuncit.backend.dto.response.RegisterWorkerResponse;

@Service
public class WorkerService {
    
    @Autowired
    private WorkerRepository workerRepository;

    RegisterWorkerResponse response = new RegisterWorkerResponse();

    public ResponseEntity<?> registerWorker(WorkerDTO worker){

        Optional<WorkerEntity> existingEmail = workerRepository.findByWorkerEmail(worker.getWorkerEmail());

        if(existingEmail.isPresent()){
            response.setResponseMessage("GSS004");
            response.setExistingEmail(worker.getWorkerEmail());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
            
        }
        else{

            WorkerEntity newWorker = new WorkerEntity(
                worker.getWorkerName(),
                worker.getWorkerAddress(),
                worker.getWorkerPhoneNumber(),
                worker.getWorkerGender(),
                worker.getWorkerEmail(),
                worker.getCreatedOn(),
                worker.getCreatedBy(),
                worker.getLastModifiedOn(),
                worker.getLastModifiedBy(),
                worker.getUserId()
            );

            workerRepository.save(newWorker);

            response.setResponseMessage("GSS003");
            response.setExistingEmail(null);

            return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
        }


    }
}
