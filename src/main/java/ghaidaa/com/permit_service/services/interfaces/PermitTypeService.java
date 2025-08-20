package ghaidaa.com.permit_service.services.interfaces;

import ghaidaa.com.permit_service.dtos.request.PermitTypeRequest;
import ghaidaa.com.permit_service.dtos.response.PermitTypeResponse;

import java.util.List;

public interface PermitTypeService {

    PermitTypeResponse create(PermitTypeRequest request);

    List<PermitTypeResponse> getAll();

    PermitTypeResponse update(Long id, PermitTypeRequest request);

    void delete(Long id);
}
