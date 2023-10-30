package kedairuncit.backend.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserEntity {
    

    @Id
    @Column(name="user_id")
    private String userId = UUID.randomUUID().toString();

    @Column(name="user_icNumber")
    private String userIcNumber;

    @Column(name="user_password")
    private String userPassword;

    @Column(name =  "user_role")
    private String userRole;

    @Column(name =  "createdOn")
    private LocalDateTime createdOn;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "lastModifiedOn")
    private LocalDateTime lastModifiedOn;

    @Column(name =  "lastModifiedBy")
    private String lastModifiedBy;

    public UserEntity(String userIcNumber, String userPassword, String userRole, LocalDateTime createdOn,
            String createdBy, LocalDateTime lastModifiedOn, String lastModifiedBy) {
        this.userIcNumber = userIcNumber;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.lastModifiedOn = lastModifiedOn;
        this.lastModifiedBy = lastModifiedBy;
    }    

    
}
