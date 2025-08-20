package ghaidaa.com.permit_service.controllers;


import ghaidaa.com.permit_service.dtos.ApiResponse;
import ghaidaa.com.permit_service.dtos.request.PermitTypeRequest;
import ghaidaa.com.permit_service.dtos.response.PermitTypeResponse;
import ghaidaa.com.permit_service.services.interfaces.PermitTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permit-types")
@RequiredArgsConstructor
public class PermitTypeController {


    @Autowired
    private PermitTypeService service;

    @PostMapping
    public ResponseEntity<ApiResponse<PermitTypeResponse>> create(@RequestBody PermitTypeRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Created successfully", service.create(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermitTypeResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success("Fetched successfully", service.getAll()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PermitTypeResponse>> update(@PathVariable Long id,
                                                                  @RequestBody PermitTypeRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Updated successfully", service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Deleted successfully", null));
    }
}
