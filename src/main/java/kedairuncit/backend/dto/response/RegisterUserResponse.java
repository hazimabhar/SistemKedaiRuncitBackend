package kedairuncit.backend.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserResponse {

    private String responseMessage;
    private String userId;
    private String existingIcNumber;

    public ResponseEntity<RegisterUserResponse> userRegistration(){

        RegisterUserResponse customResponse = new RegisterUserResponse();
        customResponse.setResponseMessage(responseMessage);
        customResponse.setUserId(userId);
        customResponse.setExistingIcNumber(existingIcNumber);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customResponse);
    }    
}
