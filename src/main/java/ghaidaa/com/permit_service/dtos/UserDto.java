package ghaidaa.com.permit_service.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record UserDto(
        UUID id,
        String fullName,
        String email,
        String phone,
        String nationalId,
        LocalDate dateOfBirth,
        String address
) {
}
