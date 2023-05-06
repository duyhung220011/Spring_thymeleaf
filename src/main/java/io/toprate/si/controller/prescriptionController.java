package io.toprate.si.controller;

import io.toprate.si.dto.PrescriptionRequest;
import io.toprate.si.dto.ServiceRequest;
import io.toprate.si.dto.UpdatePrescriptionRequest;
import io.toprate.si.dto.UpdateServiceRequest;
import io.toprate.si.messeger.ResponseObject;
import io.toprate.si.service.PrescriptionService;
import io.toprate.si.service.serviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
@RestController
@RequestMapping({"/prescription"})
@AllArgsConstructor
public class prescriptionController {
    private final PrescriptionService prescriptionService;


    @GetMapping("/filter")
    ResponseEntity<?> filter(
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "1") @Min(value = 1, message = "k tim thay trang") int page,
            @RequestParam(name = "desc", defaultValue = "true") boolean desc,
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy
    ) {
        return ResponseObject.success(prescriptionService.filter( size, page, desc, orderBy));
    }

    @GetMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> getPrescriptionById(@PathVariable("id") Integer id) {
        return ResponseObject.success(prescriptionService.getDetailPrescription(id));
    }

    @GetMapping({"/all"})
    @ResponseBody
    ResponseEntity<?> getAllPrescription() {
        return ResponseObject.success(prescriptionService.getAllPrescription());
    }


    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> insertPrescription(@RequestPart("Prescription") @Valid PrescriptionRequest prescriptionRequest) {
        return ResponseObject.createSuccess(prescriptionService.createPrescription(prescriptionRequest));
    }

    @PutMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> updatePrescription(@RequestPart("Prescription") @Valid UpdatePrescriptionRequest updatePrescriptionRequest) {
        return ResponseObject.createSuccess(prescriptionService.update(updatePrescriptionRequest));
    }

    @DeleteMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> removePrescription(@PathVariable("id") Integer id) {
        prescriptionService.delete(id);
        return ResponseObject.success(id);
    }
}
