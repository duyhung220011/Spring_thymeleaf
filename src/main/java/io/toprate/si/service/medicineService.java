package io.toprate.si.service;

import io.toprate.si.dto.MedicineRequest;
import io.toprate.si.dto.updateMedicineRequest;
import io.toprate.si.models.Medicine;
import io.toprate.si.repository.MedicineRepository;
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
public class medicineService {
    private final MedicineRepository medicineRepository;
    private final SpecificationFilter specificationFilter;

    public List<Medicine> getAllMedicine() {
        return medicineRepository.findAll();
    }

    public Medicine createMedicine(MedicineRequest medicineRequest) {
        Medicine medicine = new Medicine();
        medicine.setNameMedicine(medicineRequest.getNameMedicine());
        medicine.setUses(medicineRequest.getUses());
        medicine.setIngredient(medicineRequest.getIngredient());
        medicine.setPrice(medicineRequest.getPrice());
        medicine.setUseObject(medicineRequest.getUseObject());
        medicineRepository.save(medicine);
        return medicine;
    }

    public Medicine getDetailMedicine(Integer id) {
        return medicineRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
    }


    public Medicine update(updateMedicineRequest updateMedicineRequest) {
        Medicine medicineUpdate = medicineRepository.findById(updateMedicineRequest.getId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
        medicineUpdate.setNameMedicine(updateMedicineRequest.getNameMedicine());
        medicineUpdate.setUseObject(updateMedicineRequest.getUseObject());
        medicineUpdate.setUses(updateMedicineRequest.getUses());
        medicineUpdate.setPrice(updateMedicineRequest.getPrice());
        medicineUpdate.setIngredient(updateMedicineRequest.getIngredient());
        medicineRepository.save(medicineUpdate);
        return medicineUpdate;
    }

    public void delete(Integer id) {
        if (!medicineRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay id");
        }
        medicineRepository.deleteById(id);
    }

    public ListResult<Medicine> filter(String name, int size, int page, boolean desc, String orderBy) {
        Specification<Medicine> documentPartnerSpecification = specificationFilter.create(name);
        return ListResult.from(medicineRepository.findAll(documentPartnerSpecification, pageable(page, size, orderBy, desc)));
    }

}
