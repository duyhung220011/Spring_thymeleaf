package io.toprate.si.controller;

import io.toprate.si.dto.PatientRequest;
import io.toprate.si.dto.PatientRequest;
import io.toprate.si.dto.updatePatientRequest;
import io.toprate.si.dto.updatePatientRequest;
import io.toprate.si.messeger.ResponseObject;
import io.toprate.si.service.patientService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
@RestController
@RequestMapping({"/patient"})
@AllArgsConstructor
public class patientController {
    private final patientService patientservice;


    @GetMapping("/filter")
    ResponseEntity<?> filter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "1") @Min(value = 1, message = "k tim thay trang") int page,
            @RequestParam(name = "desc", defaultValue = "true") boolean desc,
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy
    ) {
        return ResponseObject.success(patientservice.filter(name, size, page, desc, orderBy));
    }

    @GetMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> getPatientById(@PathVariable("id") Integer id) {
        return ResponseObject.success(patientservice.getDetailPatient(id));
    }

    @GetMapping({"/all"})
    @ResponseBody
    ResponseEntity<?> getAllPatient() {
        return ResponseObject.success(patientservice.getAllPatient());
    }


    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> insertPatient(@RequestPart("Patient") @Valid PatientRequest patientRequest) {
        return ResponseObject.createSuccess(patientservice.createPatient(patientRequest));
    }

    @PutMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> updatePatient(@RequestPart("Patient") @Valid updatePatientRequest updatepatientRequest) {
        return ResponseObject.createSuccess(patientservice.update(updatepatientRequest));
    }

    @DeleteMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> removePatient(@PathVariable("id") Integer id) {
        patientservice.delete(id);
        return ResponseObject.success(id);
    }
}
