package kedairuncit.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kedairuncit.backend.dto.WorkerDTO;
import kedairuncit.backend.service.WorkerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/workers")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerWorker(@RequestBody WorkerDTO worker){
        return ResponseEntity.ok(workerService.registerWorker(worker));
    }
    
}
