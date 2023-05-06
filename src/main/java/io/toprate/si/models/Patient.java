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
@Table(name = "Patient")
@Data
@Entity
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private DateTime dateOfBirth;

    private String gender;

    private String email;

    private String phoneNumber;

    private String address;

    private String city;

    private String district;

    private String userName ;

    private String password;

    private String imgPatient;

    private String Role;


}
