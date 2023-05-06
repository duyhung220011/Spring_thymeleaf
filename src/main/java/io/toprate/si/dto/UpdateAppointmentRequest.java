package io.toprate.si.dto;

import com.google.api.client.util.DateTime;
import io.toprate.si.utils.IntegerListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppointmentRequest {

    private Integer id;

    private Integer doctorId;

    private Integer specialityId;

    private Integer patientID;

    private DateTime appointmentTime;

    @Lob
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> listServiceId;


    private Boolean approval;
}
