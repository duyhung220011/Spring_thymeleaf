package io.toprate.si.models;

import com.google.api.client.util.DateTime;
import io.toprate.si.utils.IntegerListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Appointment")
@Data
@Entity
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer doctorId;

    private Integer specialityId;

    private Integer patientID;

    private DateTime appointmentTime;

    @Lob
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> listServiceId;

    private Integer amount;

    private Boolean approval;
}
