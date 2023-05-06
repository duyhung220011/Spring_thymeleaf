package io.toprate.si.dto;

import com.google.api.client.util.DateTime;
import io.toprate.si.utils.IntegerListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Lob;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class updatePatientRequest {

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

    private String imgPatient;

}
