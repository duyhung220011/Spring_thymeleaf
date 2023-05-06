package io.toprate.si.controller;

import io.toprate.si.dto.PatientRequest;
import io.toprate.si.dto.ServiceRequest;
import io.toprate.si.dto.UpdateServiceRequest;
import io.toprate.si.dto.updatePatientRequest;
import io.toprate.si.messeger.ResponseObject;
import io.toprate.si.service.patientService;
import io.toprate.si.service.serviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping({"/service"})
@AllArgsConstructor
public class ServiceController {
    private final serviceService serviceService;


    @GetMapping("/filter")
    ResponseEntity<?> filter(
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "1") @Min(value = 1, message = "k tim thay trang") int page,
            @RequestParam(name = "desc", defaultValue = "true") boolean desc,
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy
    ) {
        return ResponseObject.success(serviceService.filter( size, page, desc, orderBy));
    }

    @GetMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> getServiceById(@PathVariable("id") Integer id) {
        return ResponseObject.success(serviceService.getDetailService(id));
    }

    @GetMapping({"/all"})
    @ResponseBody
    ResponseEntity<?> getAllService() {
        return ResponseObject.success(serviceService.getAllService());
    }


    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> insertService(@RequestPart("Service") @Valid ServiceRequest serviceRequest) {
        return ResponseObject.createSuccess(serviceService.createService(serviceRequest));
    }

    @PutMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> updateService(@RequestPart("Service") @Valid UpdateServiceRequest updateServiceRequest) {
        return ResponseObject.createSuccess(serviceService.update(updateServiceRequest));
    }

    @DeleteMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> removeService(@PathVariable("id") Integer id) {
        serviceService.delete(id);
        return ResponseObject.success(id);
    }
}
