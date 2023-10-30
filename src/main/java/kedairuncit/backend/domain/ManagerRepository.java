package kedairuncit.backend.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ManagerRepository extends JpaRepository<ManagerEntity, String>, JpaSpecificationExecutor<ManagerEntity>  {
  
        Optional<ManagerEntity> findByManagerEmail(String managerEmail);

}