package kedairuncit.backend.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterManagerResponse {
    private String responseMessage;
    private String existingEmail;

    public ResponseEntity<RegisterManagerResponse> managerRegistration(){


        RegisterManagerResponse customResponse = new RegisterManagerResponse();
        customResponse.setResponseMessage(responseMessage);
        customResponse.setExistingEmail(existingEmail);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customResponse);
    }    
}
