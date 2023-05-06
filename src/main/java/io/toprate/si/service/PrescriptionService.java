package io.toprate.si.service;

import com.google.api.client.util.DateTime;
import io.toprate.si.dto.PrescriptionRequest;
import io.toprate.si.dto.UpdatePrescriptionRequest;
import io.toprate.si.models.Prescription;
import io.toprate.si.repository.PrescriptionRepository;
import io.toprate.si.repository.PrescriptionRepository;
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
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final SpecificationFilter specificationFilter;

    public List<Prescription> getAllPrescription() {
        return prescriptionRepository.findAll();
    }

    public Prescription createPrescription(PrescriptionRequest prescriptionRequest) {
        Prescription prescriptions = new Prescription();
        setPrescription(prescriptions, prescriptionRequest.getNamePrescription(), prescriptionRequest.getDoctorId(), prescriptionRequest.getListMedicineId());
        prescriptionRepository.save(prescriptions);
        return prescriptions;
    }

    public Prescription getDetailPrescription(Integer id) {
        return prescriptionRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
    }


    public Prescription update(UpdatePrescriptionRequest prescriptionRequest) {
        Prescription prescriptions = prescriptionRepository.findById(prescriptionRequest.getId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
        setPrescription(prescriptions, prescriptionRequest.getNamePrescription(), prescriptionRequest.getDoctorId(), prescriptionRequest.getListMedicineId());
        return prescriptions;
    }

    private void setPrescription(Prescription prescription,  String namePrescription, Integer doctorId , List<Integer> listMedicineId) {
        prescription.setNamePrescription(namePrescription);
        prescription.setDoctorId(doctorId);
        prescription.setListMedicineId(listMedicineId);

    }

    public void delete(Integer id) {
        if (!prescriptionRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay id");
        }
        prescriptionRepository.deleteById(id);
    }

    public ListResult<Prescription> filter(int size, int page, boolean desc, String orderBy) {
        return ListResult.from(prescriptionRepository.findAll( pageable(page, size, orderBy, desc)));
    }
}
