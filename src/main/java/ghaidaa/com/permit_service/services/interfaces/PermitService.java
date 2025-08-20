package ghaidaa.com.permit_service.services.interfaces;


import ghaidaa.com.permit_service.dtos.request.PermitRequest;
import ghaidaa.com.permit_service.dtos.response.PermitResponse;
import ghaidaa.com.permit_service.enums.PaymentStatus;
import ghaidaa.com.permit_service.enums.PermitStatus;

import java.util.List;
import java.util.UUID;

public interface PermitService {

    PermitResponse create(PermitRequest request);

    List<PermitResponse> getAll();

    List<PermitResponse> getByApplicant(UUID applicantId);

    PermitResponse updateStatus(UUID id, String status);

    PermitResponse updatePaymentStatus(UUID id, String status);


}
