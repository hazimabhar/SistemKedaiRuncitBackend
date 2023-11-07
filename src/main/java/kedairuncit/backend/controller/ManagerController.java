package kedairuncit.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kedairuncit.backend.dto.ManagerDTO;
import kedairuncit.backend.service.ManagerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/managers")
@RequiredArgsConstructor
public class ManagerController {
    
    private final ManagerService managerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerManager(@RequestBody ManagerDTO manager) {
        return ResponseEntity.ok(managerService.registerManager(manager));
    }
}
