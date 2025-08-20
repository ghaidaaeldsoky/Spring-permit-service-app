package ghaidaa.com.permit_service.services.impls;


import ghaidaa.com.permit_service.dtos.request.PermitTypeRequest;
import ghaidaa.com.permit_service.dtos.response.PermitTypeResponse;
import ghaidaa.com.permit_service.entities.PermitType;
import ghaidaa.com.permit_service.exceptions.ResourceNotFoundException;
import ghaidaa.com.permit_service.mappers.PermitTypeMapper;
import ghaidaa.com.permit_service.repos.PermitTypeRepo;
import ghaidaa.com.permit_service.services.interfaces.PermitTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermitTypeServiceImpl implements PermitTypeService {

    @Autowired
    private PermitTypeRepo permitTypeRepo;

    @Autowired
    private PermitTypeMapper permitTypeMapper;

    @Override
    @Transactional
    public PermitTypeResponse create(PermitTypeRequest request) {
        PermitType entity = permitTypeMapper.toEntity(request);
        return permitTypeMapper.toResponse(permitTypeRepo.save(entity));
    }

    @Override
    public List<PermitTypeResponse> getAll() {
        return permitTypeRepo.findAll()
                .stream()
                .map(permitTypeMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PermitTypeResponse update(Long id, PermitTypeRequest request) {
        PermitType type = permitTypeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PermitType not found"));
        type.setName(request.name());
        type.setBaseFee(request.baseFee());
        return permitTypeMapper.toResponse(permitTypeRepo.save(type));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!permitTypeRepo.existsById(id)) {
            throw new ResourceNotFoundException("PermitType not found");
        }
        permitTypeRepo.deleteById(id);
    }

}
