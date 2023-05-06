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
@Table(name = "Doctor")
@Data
@Entity
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String Role;

    private DateTime dateOfBirth;

    private String gender;

    private String email;

    private String phoneNumber;

    private String address;

    private String city;

    private String district;

    private Boolean accountStatus;

    private String userName ;

    private String password;

    private String imgDoctor;

    private String description;

    @Lob
    @Convert(converter = IntegerListConverter.class)
    private List<Integer> specialityId;

}
