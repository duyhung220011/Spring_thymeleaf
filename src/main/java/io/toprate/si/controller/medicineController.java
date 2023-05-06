package io.toprate.si.controller;

import io.toprate.si.dto.MedicineRequest;
import io.toprate.si.dto.updateMedicineRequest;
import io.toprate.si.messeger.ResponseObject;
import io.toprate.si.service.medicineService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping({"/medicine"})
@AllArgsConstructor
public class medicineController {
    private final medicineService medicineservice;

    @GetMapping("/filter")
    ResponseEntity<?> filter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "page", defaultValue = "1") @Min(value = 1, message = "k tim thay trang") int page,
            @RequestParam(name = "desc", defaultValue = "true") boolean desc,
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy
    ) {
        return ResponseObject.success(medicineservice.filter(name, size, page, desc, orderBy));
    }

    @GetMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> getMedicineById(@PathVariable("id") Integer id) {
        return ResponseObject.success(medicineservice.getDetailMedicine(id));
    }

    @GetMapping({"/all"})
    @ResponseBody
    ResponseEntity<?> getAllMedicine() {
        return ResponseObject.success(medicineservice.getAllMedicine());
    }


    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> insertMedicine(@RequestPart("partner") @Valid MedicineRequest medicineRequest) {
        return ResponseObject.createSuccess(medicineservice.createMedicine(medicineRequest));
    }

    @PutMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> updateMedicine(@RequestPart("partner") @Valid updateMedicineRequest updateMedicineRequest) {
        return ResponseObject.createSuccess(medicineservice.update(updateMedicineRequest));
    }

    @DeleteMapping({"/{id}"})
    @ResponseBody
    ResponseEntity<?> removeMedicine(@PathVariable("id") Integer id) {
        medicineservice.delete(id);
        return ResponseObject.success(id);
    }

}
