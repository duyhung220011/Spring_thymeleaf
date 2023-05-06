package io.toprate.si.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class updateMedicineRequest {
    private Integer id;

    private String nameMedicine;

    private String uses;

    private String ingredient;

    private String useObject;

    private Integer price;
}
