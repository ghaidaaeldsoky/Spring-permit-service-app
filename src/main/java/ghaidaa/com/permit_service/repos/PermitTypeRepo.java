package ghaidaa.com.permit_service.repos;


import ghaidaa.com.permit_service.entities.PermitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermitTypeRepo extends JpaRepository<PermitType, Long> {

    Optional<PermitType> findByCode(String code);

    boolean existsByCode(String code);
}
