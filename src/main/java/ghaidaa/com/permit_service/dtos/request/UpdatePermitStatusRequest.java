package ghaidaa.com.permit_service.dtos.request;

import ghaidaa.com.permit_service.enums.PermitStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePermitStatusRequest(
        @NotBlank(message = "Permit Status cannot be blank")
        String Status
) {
}
