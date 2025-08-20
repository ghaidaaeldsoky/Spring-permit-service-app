package ghaidaa.com.permit_service.dtos.response;

import ghaidaa.com.permit_service.enums.PaymentStatus;
import ghaidaa.com.permit_service.enums.PermitStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PermitResponse(
        UUID id,
        UUID applicantId,
        PermitTypeResponse type,
        PermitStatus status,
        BigDecimal feeAmount,
        PaymentStatus paymentStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
