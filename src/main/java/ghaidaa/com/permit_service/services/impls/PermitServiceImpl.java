package ghaidaa.com.permit_service.services.impls;

import ghaidaa.com.permit_service.dtos.ApiResponse;
import ghaidaa.com.permit_service.dtos.request.PermitRequest;
import ghaidaa.com.permit_service.dtos.response.PermitResponse;
import ghaidaa.com.permit_service.entities.Permit;
import ghaidaa.com.permit_service.entities.PermitType;
import ghaidaa.com.permit_service.enums.PaymentStatus;
import ghaidaa.com.permit_service.enums.PermitStatus;
import ghaidaa.com.permit_service.exceptions.InvalidOperationException;
import ghaidaa.com.permit_service.exceptions.ResourceNotFoundException;
import ghaidaa.com.permit_service.mappers.PermitMapper;
import ghaidaa.com.permit_service.repos.PermitRepo;
import ghaidaa.com.permit_service.repos.PermitTypeRepo;
import ghaidaa.com.permit_service.services.interfaces.PermitService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermitServiceImpl implements PermitService {

    @Autowired
    private PermitRepo permitRepository;
    @Autowired
    private PermitTypeRepo permitTypeRepository;
    @Autowired
    private PermitMapper permitMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.service.base-url}")
    private String userServiceBaseUrl;

//    private static final String USER_SERVICE_URL = "http://user-service:8082/api/v1/admin/users/"; // TODO: change to service-discovery later


    @Override
    @Transactional
    public PermitResponse create(PermitRequest request) {
        // Check if this ApplicantId is valid from user-service
        System.out.println("Creating new permit");
        System.out.println("Base Url: "+userServiceBaseUrl);
//        var url = userServiceBaseUrl + "/api/v1/admin/users/";
        String userCheckUrl = String.format("%s/%s", userServiceBaseUrl, request.applicantId());
        try{
            var userResponse = restTemplate.getForObject(
                    userCheckUrl,
                    ApiResponse.class
            );
            System.out.println("After getting user response");
            if (userResponse == null || !"success".equals(userResponse.status())) {
                throw new ResourceNotFoundException("Applicant not found in user-service");
            }
        } catch (RestClientException e) {
            throw new ResourceNotFoundException("User service is temporarily unavailable");
        }


        PermitType type = permitTypeRepository.findById(request.typeId())
                .orElseThrow(() -> new ResourceNotFoundException("PermitType not found"));

        Permit permit = permitMapper.toEntity(request);
        permit.setType(type);
        permit.setFeeAmount(type.getBaseFee());
        permit.setPaymentStatus(PaymentStatus.PENDING);

        return permitMapper.toResponse(permitRepository.save(permit));
    }

    @Override
    public List<PermitResponse> getAll() {
        return permitRepository.findAll()
                .stream()
                .map(permitMapper::toResponse)
                .toList();
    }

    @Override
    public List<PermitResponse> getByApplicant(UUID applicantId) {
        return permitRepository.findByApplicantId(applicantId)
                .stream()
                .map(permitMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PermitResponse updateStatus(UUID id, String status) {
        Permit permit = permitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permit not found"));
        System.out.println("After getting user response before updating status");
        PermitStatus permitStatus;
        try {
            permitStatus = PermitStatus.valueOf(status.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new InvalidOperationException("Invalid permit status");
        }
        permit.setStatus(permitStatus);
        System.out.println("After getting user response after updating payment status");
        return permitMapper.toResponse(permitRepository.save(permit));
    }

    @Override
    @Transactional
    public PermitResponse updatePaymentStatus(UUID id, String status) {
        Permit permit = permitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permit not found"));
        System.out.println("After getting user response before updating payment status");
        PaymentStatus paymentStatus ;
        try{
            paymentStatus = PaymentStatus.valueOf(status.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new InvalidOperationException("Invalid payment status. Allowed values: " + Arrays.toString(PaymentStatus.values()));
        }
        permit.setPaymentStatus(paymentStatus);
        System.out.println("After getting user response after updating payment status");
        return permitMapper.toResponse(permitRepository.save(permit));
    }

}
