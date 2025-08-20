package ghaidaa.com.permit_service.mappers;

import ghaidaa.com.permit_service.dtos.request.PermitRequest;
import ghaidaa.com.permit_service.dtos.response.PermitResponse;
import ghaidaa.com.permit_service.entities.Permit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {PermitTypeMapper.class})
public interface PermitMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "paymentStatus", constant = "PENDING")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Permit toEntity(PermitRequest request);

    PermitResponse toResponse(Permit entity);
}
