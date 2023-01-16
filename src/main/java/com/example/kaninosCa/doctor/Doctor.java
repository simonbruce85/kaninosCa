package com.example.kaninosCa.doctor;

import com.example.kaninosCa.pet.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@AllArgsConstructor
@EqualsAndHashCode
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
    private Long id;
    private String name;
    private String phone;

    @JsonIgnoreProperties({"doctor", "visits"})
    @OneToMany(
            mappedBy = "doctor",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Pet> pets = new ArrayList<>();

    public void addPet(Pet pet) {
        if (!this.pets.contains(pet)) {
            this.pets.add(pet);
            pet.setDoctor(this);
        }
    }

    public void removePet(Pet pet) {
        if (this.pets.contains(pet)) {
            this.pets.remove(pet);
        }
        pet.setDoctor(null);
    }


}
