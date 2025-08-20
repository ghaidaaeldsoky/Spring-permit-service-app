package ghaidaa.com.permit_service.mappers;

import ghaidaa.com.permit_service.dtos.request.PermitTypeRequest;
import ghaidaa.com.permit_service.dtos.response.PermitTypeResponse;
import ghaidaa.com.permit_service.entities.PermitType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermitTypeMapper {

    PermitType toEntity(PermitTypeRequest request);

    PermitTypeResponse toResponse(PermitType entity);

}
