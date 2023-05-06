package io.toprate.si.dto;

import io.toprate.si.utils.IntegerListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePrescriptionRequest {

    private Integer id;

    private String  namePrescription;

    private Integer doctorId;

    @Lob
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> listMedicineId;

}
