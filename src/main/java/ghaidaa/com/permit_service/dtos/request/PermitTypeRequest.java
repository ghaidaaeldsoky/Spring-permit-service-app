package ghaidaa.com.permit_service.dtos.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PermitTypeRequest(
        @NotBlank(message = "Code is required")
        @Size(max = 20, message = "Code must be at most 20 characters")
        String code,

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be at most 100 characters")
        String name,

        @NotNull(message = "Base fee is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Fee must be greater than 0")
        BigDecimal baseFee
        ) {
}
