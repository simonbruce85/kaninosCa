package com.example.kaninosCa.doctor;

import com.example.kaninosCa.pet.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@EqualsAndHashCode(exclude = {"pets"})
public class Doctor {

    @Id
    @SequenceGenerator(
            name="doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "doctor_sequence",
            strategy = GenerationType.IDENTITY
    )
    private Long doctorId;


    private String name;
    private String phone;

    public Doctor(Long doctorId, String name, String phone) {
        this.doctorId = doctorId;
        this.name = name;
        this.phone = phone;
    }
}
