package kedairuncit.backend.domain;

import java.time.LocalDateTime;
import java.util.UUID;

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
@Table(name="workers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class WorkerEntity {

    @Id
    @Column(name="worker_id")
    private String workerId = UUID.randomUUID().toString();

    @Column(name = "worker_name")
    private String workerName;

    @Column(name="worker_address")
    private String workerAddress;

    @Column(name="worker_phone_number")
    private String workerPhoneNumber;

    @Column(name="worker_gender")
    private String workerGender;

    @Column(name="worker_email")
    private String workerEmail;

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

    public WorkerEntity(String workerName, String workerAddress, String workerPhoneNumber, String workerGender,
            String workerEmail, LocalDateTime createdOn, String createdBy, LocalDateTime lastModifiedOn,
            String lastModifiedBy, String userId) {
        this.workerName = workerName;
        this.workerAddress = workerAddress;
        this.workerPhoneNumber = workerPhoneNumber;
        this.workerGender = workerGender;
        this.workerEmail = workerEmail;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.lastModifiedOn = lastModifiedOn;
        this.lastModifiedBy = lastModifiedBy;
        this.userId = userId;
    }

    
    
}
