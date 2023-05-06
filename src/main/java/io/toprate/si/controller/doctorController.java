package io.toprate.si.controller;

import io.toprate.si.dto.DoctorRequest;
import io.toprate.si.dto.updateDoctorRequest;
import io.toprate.si.messeger.ResponseObject;
import io.toprate.si.service.doctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
@RestController
@RequestMapping({"/doctor"})
@AllArgsConstructor
public class doctorController {
    private final doctorService doctorservice;


    @GetMapping("/filter")
    ResponseEntity<?> filter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "1") @Min(value = 1, message = "k tim thay trang") int page,
            @RequestParam(name = "desc", defaultValue = "true") boolean desc,
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy
    ) {
        return ResponseObject.success(doctorservice.filter(name,gender, size, page, desc, orderBy));
    }

    @GetMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> getDoctorById(@PathVariable("id") Integer id) {
        return ResponseObject.success(doctorservice.getDetailDoctor(id));
    }

    @GetMapping({"/all"})
    @ResponseBody
    ResponseEntity<?> getAllDoctor() {
        return ResponseObject.success(doctorservice.getAllDoctor());
    }


    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> insertDoctor(@RequestPart("doctor") @Valid DoctorRequest doctorRequest) {
        return ResponseObject.createSuccess(doctorservice.createDoctor(doctorRequest));
    }

    @PutMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> updateDoctor(@RequestPart("doctor") @Valid updateDoctorRequest updateDoctorRequest) {
        return ResponseObject.createSuccess(doctorservice.update(updateDoctorRequest));
    }

    @DeleteMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> removeDoctor(@PathVariable("id") Integer id) {
        doctorservice.delete(id);
        return ResponseObject.success(id);
    }

}
