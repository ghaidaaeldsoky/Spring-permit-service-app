package ghaidaa.com.permit_service.dtos.request;

import ghaidaa.com.permit_service.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePaymentStatusRequest(
        @NotBlank(message = "Payment Status cannot be blank")
        String paymentStatus
) {
}
