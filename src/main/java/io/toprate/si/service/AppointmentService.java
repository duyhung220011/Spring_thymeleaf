package io.toprate.si.service;

import com.google.api.client.util.DateTime;
import io.toprate.si.dto.AppointmentRequest;
import io.toprate.si.dto.UpdateAppointmentRequest;
import io.toprate.si.models.Appointment;
import io.toprate.si.repository.AppointmentRepository;
import io.toprate.si.repository.ServiceRepository;
import io.toprate.si.utils.ListResult;
import io.toprate.si.utils.SpecificationFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static io.toprate.si.utils.PageableUtils.pageable;
@Service
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ServiceRepository serviceRepository;
    private final SpecificationFilter specificationFilter;
    private Integer amount = 0 ;
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        setAppointment(appointment, appointmentRequest.getDoctorId(), appointmentRequest.getSpecialityId(), appointmentRequest.getPatientID(), appointmentRequest.getAppointmentTime(), appointmentRequest.getListServiceId());
        appointment.setApproval(null);
        appointmentRepository.save(appointment);
        return appointment;
    }

    public Appointment getDetailAppointment(Integer id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
    }


    public Appointment update(UpdateAppointmentRequest updateAppointmentRequest) {
        Appointment appointment = appointmentRepository.findById(updateAppointmentRequest.getId()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay"));
        setAppointment(appointment, updateAppointmentRequest.getDoctorId(), updateAppointmentRequest.getSpecialityId(), updateAppointmentRequest.getPatientID(), updateAppointmentRequest.getAppointmentTime(), updateAppointmentRequest.getListServiceId());
        appointmentRepository.save(appointment);
        return appointment;
    }

    private void setAppointment(Appointment appointment, Integer doctorId, Integer specialityId, Integer patientID, DateTime appointmentTime, List<Integer> listServiceId) {
        appointment.setDoctorId(doctorId);
        appointment.setSpecialityId(specialityId);
        appointment.setPatientID(patientID);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setListServiceId(listServiceId);
        if(listServiceId != null){
        listServiceId.forEach(id -> {
            io.toprate.si.models.Service service = serviceRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Không có dịch vụ "));
            amount += service.getPrice();
        });}
        appointment.setAmount(amount);
    }

    public Appointment approval(Integer idAppointment) {
        Appointment appointment = appointmentRepository.findById(idAppointment).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Không tồn tại"));
        appointment.setApproval(true);
        appointmentRepository.save(appointment);
        return appointment;
    }

    public Appointment reject(Integer idAppointment) {
        Appointment appointment = appointmentRepository.findById(idAppointment).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Không tồn tại"));
        appointment.setApproval(false);
        appointmentRepository.save(appointment);
        return appointment;
    }

    public void delete(Integer id) {
        if (!appointmentRepository.existsById(id)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Khong tim thay id");
        }
        appointmentRepository.deleteById(id);
    }

    public ListResult<Appointment> filter(Integer idDoctor, Integer idPatient,  int size, int page, boolean desc, String orderBy) {
        Specification<Appointment> documentAppoinmentSpecification = specificationFilter.appoinmentSpecification(idDoctor, idPatient);
        return ListResult.from(appointmentRepository.findAll(documentAppoinmentSpecification, pageable(page, size, orderBy, desc)));
    }
}
