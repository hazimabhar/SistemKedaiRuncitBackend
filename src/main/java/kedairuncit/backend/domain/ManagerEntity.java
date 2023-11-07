package kedairuncit.backend.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kedairuncit.backend.dto.ManagerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="managers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ManagerEntity {
    
    @Id
    @Column(name="manager_id")
    private String managerId = UUID.randomUUID().toString();

    @Column(name = "manager_name")
    private String managerName;

    @Column(name="manager_address")
    private String managerAddress;

    @Column(name="manager_phone_number")
    private String managerPhoneNumber;

    @Column(name="manager_gender")
    private String managerGender;

    @Column(name="manager_email")
    private String managerEmail;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name="created_by")
    private String createdBy;

    @Column(name = "last_modified_on")
    private LocalDateTime lastModifiedOn;

    @Column(name="last_modified_by")
    private String lastModifiedBy;

    @Column(name = "user_id")
    private String userId;

    public ManagerEntity(String managerName, String managerAddress, String managerPhoneNumber, String managerGender,
            String managerEmail, LocalDateTime createdOn, String createdBy, LocalDateTime lastModifiedOn,
            String lastModifiedBy, String userId) {
        this.managerName = managerName;
        this.managerAddress = managerAddress;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerGender = managerGender;
        this.managerEmail = managerEmail;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.lastModifiedOn = lastModifiedOn;
        this.lastModifiedBy = lastModifiedBy;
        this.userId = userId;
    }

    

}
