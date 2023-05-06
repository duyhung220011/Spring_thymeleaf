package io.toprate.si.service;

import com.google.api.client.util.DateTime;
import io.toprate.si.dto.DoctorRequest;
import io.toprate.si.dto.updateDoctorRequest;
import io.toprate.si.models.Doctor;
import io.toprate.si.repository.DoctorRepository;
import io.toprate.si.repository.DoctorRepository;
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
public class doctorService {
    private final DoctorRepository doctorRepository;
    private final SpecificationFilter specificationFilter;

    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(DoctorRequest doctorRequest) {
        Doctor doctors = new Doctor();
        setDoctor(doctors, doctorRequest.getFirstName(), doctorRequest.getLastName(), doctorRequest.getDateOfBirth(), doctorRequest.getEmail(), doctorRequest.getPhoneNumber(), doctorRequest.getAddress(), doctorRequest.getCity(), doctorRequest.getDistrict(), doctorRequest.getImgDoctor(),doctorRequest.getDescription(),doctorRequest.getSpecialityId());
        doctors.setUserName(doctorRequest.getUserName());
        doctors.setPassword(doctorRequest.getPassword());
        doctors.setRole("Doctor");
        doctorRepository.save(doctors);
        return doctors;
    }

    public Doctor getDetailDoctor(Integer id) {
        return doctorRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
    }


    public Doctor update(updateDoctorRequest updatedoctorRequest) {
        Doctor doctorUpdate = doctorRepository.findById(updatedoctorRequest.getId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
        setDoctor(doctorUpdate, updatedoctorRequest.getFirstName(), updatedoctorRequest.getLastName(), updatedoctorRequest.getDateOfBirth(), updatedoctorRequest.getEmail(), updatedoctorRequest.getPhoneNumber(), updatedoctorRequest.getAddress(), updatedoctorRequest.getCity(), updatedoctorRequest.getDistrict(), updatedoctorRequest.getImgDoctor(),updatedoctorRequest.getDescription(),updatedoctorRequest.getSpecialityId());
        doctorUpdate.setImgDoctor(updatedoctorRequest.getImgDoctor());
        doctorUpdate.setDescription(updatedoctorRequest.getDescription());
        doctorUpdate.setSpecialityId(updatedoctorRequest.getSpecialityId());
        return doctorUpdate;
    }

    private void setDoctor(Doctor doctorUpdate, String firstName, String lastName, DateTime dateOfBirth, String email, String phoneNumber, String address, String city, String district, String imgDoctor , String description, List<Integer> specialityId) {
        doctorUpdate.setFirstName(firstName);
        doctorUpdate.setLastName(lastName);
        doctorUpdate.setDateOfBirth(dateOfBirth);
        doctorUpdate.setEmail(email);
        doctorUpdate.setPhoneNumber(phoneNumber);
        doctorUpdate.setAddress(address);
        doctorUpdate.setCity(city);
        doctorUpdate.setDistrict(district);
        doctorUpdate.setAccountStatus(Boolean.TRUE);
        doctorUpdate.setImgDoctor(imgDoctor);
        doctorUpdate.setDescription(description);
        doctorUpdate.setSpecialityId(specialityId);
    }

    public void delete(Integer id) {
        if (!doctorRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay id");
        }
        doctorRepository.deleteById(id);
    }

    public ListResult<Doctor> filter(String name, String gender, int size, int page, boolean desc, String orderBy) {
        Specification<Doctor> documentDoctorSpecification = specificationFilter.doctorSpecification(name,gender);
        return ListResult.from(doctorRepository.findAll(documentDoctorSpecification, pageable(page, size, orderBy, desc)));
    }
}
