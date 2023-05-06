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
public class updateDoctorRequest {

    private Integer id;

    private String firstName;

    private String lastName;

    private DateTime dateOfBirth;

    private  String gender;

    private String email;

    private String phoneNumber;

    private String address;

    private String city;

    private String district;

    private Boolean accountStatus;

    private String imgDoctor;

    private String description;

    @Lob
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> specialityId;

}
