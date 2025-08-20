package ghaidaa.com.permit_service.dtos.response;

import java.math.BigDecimal;

public record PermitTypeResponse(
        Long id,
        String code,
        String name,
        BigDecimal baseFee
) {
}
