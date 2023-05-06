package io.toprate.si.service;

import com.google.api.client.util.DateTime;
import io.toprate.si.dto.DoctorRequest;
import io.toprate.si.dto.PatientRequest;
import io.toprate.si.dto.updateDoctorRequest;
import io.toprate.si.dto.updatePatientRequest;
import io.toprate.si.models.Doctor;
import io.toprate.si.models.Patient;
import io.toprate.si.repository.DoctorRepository;
import io.toprate.si.repository.PatientRepository;
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
public class patientService {
    private final PatientRepository patientRepository;
    private final SpecificationFilter specificationFilter;

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public Patient createPatient(PatientRequest patientRequest) {
        Patient patient = new Patient();
        setPatient(patientRequest, patientRequest.getFirstName(), patientRequest.getLastName(), patientRequest.getDateOfBirth(), patientRequest.getEmail(), patientRequest.getPhoneNumber(), patientRequest.getAddress(), patientRequest.getCity(), patientRequest.getDistrict(), patientRequest.getImgPatient());
        patient.setUserName(patientRequest.getUserName());
        patient.setPassword(patientRequest.getPassword());
        patient.setRole("user");
        patientRepository.save(patient);
        return patient;
    }

    public Patient getDetailPatient(Integer id) {
        return patientRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
    }


    public Patient update(updatePatientRequest updatePatientRequest) {
        Patient patientUpdate = patientRepository.findById(updatePatientRequest.getId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
        setPatient(patientUpdate, updatePatientRequest.getFirstName(), updatePatientRequest.getLastName(), updatePatientRequest.getDateOfBirth(), updatePatientRequest.getEmail(), updatePatientRequest.getPhoneNumber(), updatePatientRequest.getAddress(), updatePatientRequest.getCity(), updatePatientRequest.getDistrict(), updatePatientRequest.getImgPatient());
        return patientUpdate;
    }



    private void setPatient(Patient patientUpdate, String firstName, String lastName, DateTime dateOfBirth, String email, String phoneNumber, String address, String city, String district, String imgPatient ) {
        patientUpdate.setFirstName(firstName);
        patientUpdate.setLastName(lastName);
        patientUpdate.setDateOfBirth(dateOfBirth);
        patientUpdate.setEmail(email);
        patientUpdate.setPhoneNumber(phoneNumber);
        patientUpdate.setAddress(address);
        patientUpdate.setCity(city);
        patientUpdate.setDistrict(district);
        patientUpdate.setImgPatient(imgPatient);
    }

    public void delete(Integer id) {
        if (!patientRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay id");
        }
        patientRepository.deleteById(id);
    }

    public ListResult<Patient> filter(String name, int size, int page, boolean desc, String orderBy) {
        Specification<Patient> documentPatientSpecification = specificationFilter.patientSpecification(name);
        return ListResult.from(patientRepository.findAll(documentPatientSpecification, pageable(page, size, orderBy, desc)));
    }
}
