package ghaidaa.com.permit_service.controllers;


import ghaidaa.com.permit_service.dtos.ApiResponse;
import ghaidaa.com.permit_service.dtos.request.PermitRequest;
import ghaidaa.com.permit_service.dtos.request.UpdatePaymentStatusRequest;
import ghaidaa.com.permit_service.dtos.request.UpdatePermitStatusRequest;
import ghaidaa.com.permit_service.dtos.response.PermitResponse;
import ghaidaa.com.permit_service.services.interfaces.PermitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/permits")
@RequiredArgsConstructor
public class PermitController {

    @Autowired
    private PermitService service;

    @PostMapping
    public ResponseEntity<ApiResponse<PermitResponse>> create(@RequestBody PermitRequest request,
                                                              @RequestHeader(value = "Authorization", required = false) String authHeader) {
        return ResponseEntity.ok(ApiResponse.success("Permit created", service.create(request, authHeader)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermitResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("Fetched successfully", service.getAll()));
    }

    @GetMapping("/applicant/{id}")
    public ResponseEntity<ApiResponse<List<PermitResponse>>> getByApplicant(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success("Fetched successfully", service.getByApplicant(id)));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<PermitResponse>> updateStatus(@PathVariable UUID id,
                                                                    @RequestParam UpdatePermitStatusRequest status) {
        System.out.println("Updating status in Controller with id " + id);
        return ResponseEntity.ok(ApiResponse.success("Status updated", service.updateStatus(id, status.Status())));
    }

    @PutMapping("/{id}/payment-status")
    public ResponseEntity<ApiResponse<PermitResponse>> updatePaymentStatus(@PathVariable UUID id,
                                                                           @RequestParam UpdatePaymentStatusRequest status) {
        System.out.println("Updating payment status in Controller with id " + id);
        return ResponseEntity.ok(ApiResponse.success("Payment status updated", service.updatePaymentStatus(id, status.paymentStatus())));
    }

    // get Permit details by id
}
