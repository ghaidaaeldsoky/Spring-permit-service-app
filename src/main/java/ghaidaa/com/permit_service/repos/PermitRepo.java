package ghaidaa.com.permit_service.repos;


import ghaidaa.com.permit_service.entities.Permit;
import ghaidaa.com.permit_service.enums.PermitStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PermitRepo extends JpaRepository<Permit, UUID> {

    // Get all permits by applicant
    List<Permit> findByApplicantId(UUID applicantId);

    // Filter permits by status
    Page<Permit> findByStatus(PermitStatus status, Pageable pageable);

    // Filter permits by typeId
    Page<Permit> findByType_Id(Long typeId, Pageable pageable);

    // Filter permits by date range
    Page<Permit> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

}
