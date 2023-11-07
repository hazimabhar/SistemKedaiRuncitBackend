package kedairuncit.backend.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticateUserResponse {
    private String responseMessage;
    private String userRole;

      public ResponseEntity<RegisterManagerResponse> managerRegistration(){


        RegisterManagerResponse customResponse = new RegisterManagerResponse();
        customResponse.setResponseMessage(responseMessage);
        customResponse.setExistingEmail(userRole);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customResponse);
    }    

    
}
