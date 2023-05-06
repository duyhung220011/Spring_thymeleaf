package io.toprate.si.controller;

import io.toprate.si.dto.AppointmentRequest;
import io.toprate.si.dto.MedicineRequest;
import io.toprate.si.dto.UpdateAppointmentRequest;
import io.toprate.si.dto.updateMedicineRequest;
import io.toprate.si.messeger.ResponseObject;
import io.toprate.si.service.AppointmentService;
import io.toprate.si.service.medicineService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping({"/appointment"})
@AllArgsConstructor
public class appointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/filter")
    ResponseEntity<?> filter(
            @RequestParam(name = "idDoctor", required = false) Integer idDoctor,
            @RequestParam(name = "idPatient", required = false) Integer idPatient,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "1") @Min(value = 1, message = "k tim thay trang") int page,
            @RequestParam(name = "desc", defaultValue = "true") boolean desc,
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy
    ) {
        return ResponseObject.success(appointmentService.filter(idDoctor,idPatient, size, page, desc, orderBy));
    }

    @GetMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> getAppointmentById(@PathVariable("id") Integer id) {
        return ResponseObject.success(appointmentService.getDetailAppointment(id));
    }

    @GetMapping({"/all"})
    @ResponseBody
    ResponseEntity<?> getAllAppointment() {
        return ResponseObject.success(appointmentService.getAllAppointment());
    }


    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> insertAppointment(@RequestPart("Appointment") @Valid AppointmentRequest appointmentRequest) {
        return ResponseObject.createSuccess(appointmentService.createAppointment(appointmentRequest));
    }

    @PutMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> updateAppointment(@RequestPart("Appointment") @Valid UpdateAppointmentRequest updateAppointmentRequest) {
        return ResponseObject.createSuccess(appointmentService.update(updateAppointmentRequest));
    }

    @PutMapping(value = "/approval")
    ResponseEntity<?> approvalAppointment(@RequestBody() @Valid Integer idAppointment) {
        return ResponseObject.createSuccess(appointmentService.approval(idAppointment));
    }

    @PutMapping(value = "/reject")
    ResponseEntity<?> rejectAppointment(@RequestBody() @Valid Integer idAppointment) {
        return ResponseObject.createSuccess(appointmentService.reject(idAppointment));
    }

    @DeleteMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> removeAppointment(@PathVariable("id") Integer id) {
        appointmentService.delete(id);
        return ResponseObject.success(id);
    }
}
