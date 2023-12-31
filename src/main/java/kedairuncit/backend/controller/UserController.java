package kedairuncit.backend.controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kedairuncit.backend.domain.UserEntity;
import kedairuncit.backend.dto.ResetPasswordDTO;
import kedairuncit.backend.dto.UserDTO;
import kedairuncit.backend.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.authenticateUser(user));
    }
    
    @PostMapping("/resetpasswordcredentials")
    public ResponseEntity<?> resetPasswordCredentials(@RequestBody ResetPasswordDTO user){
        return ResponseEntity.ok(userService.resetPasswordCredentials(user));
    }

    @PutMapping("/newpassword/{userId}")
    public ResponseEntity<?> newPassword(@PathVariable String userId, @RequestBody UserDTO user){
        return ResponseEntity.ok(userService.resetPassword(userId, user));
    }
}
