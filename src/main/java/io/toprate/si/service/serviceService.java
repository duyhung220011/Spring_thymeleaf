package io.toprate.si.service;

import io.toprate.si.dto.MedicineRequest;
import io.toprate.si.dto.ServiceRequest;
import io.toprate.si.dto.UpdateServiceRequest;
import io.toprate.si.dto.updateMedicineRequest;
import io.toprate.si.models.Appointment;
import io.toprate.si.models.Medicine;
import io.toprate.si.repository.MedicineRepository;
import io.toprate.si.repository.ServiceRepository;
import io.toprate.si.utils.ListResult;
import io.toprate.si.utils.SpecificationFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static io.toprate.si.utils.PageableUtils.pageable;

@Service
@AllArgsConstructor
public class serviceService {
    private final ServiceRepository serviceRepository;
    private final SpecificationFilter specificationFilter;

    public List<io.toprate.si.models.Service> getAllService() {
        return serviceRepository.findAll();
    }

    public io.toprate.si.models.Service createService(ServiceRequest medicineRequest) {
        io.toprate.si.models.Service service = new io.toprate.si.models.Service();
        service.setNameService(medicineRequest.getNameService());
        service.setPrice(medicineRequest.getPrice());
        serviceRepository.save(service);
        return service;
    }

    public io.toprate.si.models.Service getDetailService(Integer id) {
        return serviceRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
    }


    public io.toprate.si.models.Service update(UpdateServiceRequest updateServiceRequest) {
        io.toprate.si.models.Service service = serviceRepository.findById(updateServiceRequest.getId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
        service.setNameService(updateServiceRequest.getNameService());
        service.setPrice(updateServiceRequest.getPrice());
        serviceRepository.save(service);
        return service;
    }

    public void delete(Integer id) {
        if (!serviceRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay id");
        }
        serviceRepository.deleteById(id);
    }

    public ListResult<io.toprate.si.models.Service> filter(int size, int page, boolean desc, String orderBy) {
        return ListResult.from(serviceRepository.findAll( pageable(page, size, orderBy, desc)));
    }
}
