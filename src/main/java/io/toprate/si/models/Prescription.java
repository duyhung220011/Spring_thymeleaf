package io.toprate.si.models;

import io.toprate.si.utils.IntegerListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Prescription")
@Data
@Entity
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String  namePrescription;

    private Integer doctorId;

    @Lob
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> listMedicineId;
}
