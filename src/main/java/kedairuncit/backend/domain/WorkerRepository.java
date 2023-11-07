package kedairuncit.backend.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkerRepository extends JpaRepository<WorkerEntity, String>, JpaSpecificationExecutor<WorkerEntity>{

    Optional<WorkerEntity> findByWorkerEmail(String workerEmail);
    
}
