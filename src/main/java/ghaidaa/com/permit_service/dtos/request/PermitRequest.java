package ghaidaa.com.permit_service.dtos.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record PermitRequest(
        @NotNull(message = "ApplicantId is required")
        UUID applicantId, // from User service

        @NotNull(message = "Permit type is required")
        Long typeId,

        @NotNull(message = "Fee amount is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Fee must be greater than 0")
        BigDecimal feeAmount
) {
}
