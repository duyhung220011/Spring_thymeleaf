package io.toprate.si.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Specialities")
@Data
@Entity
@Builder
public class Specialities {
    @Id
    @GeneratedValue
    private Integer id;

    private String nameSpeciality;

    private String img;


}
