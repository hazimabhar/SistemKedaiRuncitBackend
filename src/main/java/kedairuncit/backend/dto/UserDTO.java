package kedairuncit.backend.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable {

    private String userId;
    
    private String userIcNumber;

    private String userPassword;

    private String userRole;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdOn;

    private String createdBy;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime lastModifiedOn;

    private String lastModifiedBy;

    public void setUserPassword(String userPassword) {

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        this.userPassword = bcrypt.encode(userPassword);
      }
}
