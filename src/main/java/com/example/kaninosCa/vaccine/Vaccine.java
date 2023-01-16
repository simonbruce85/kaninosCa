package com.example.kaninosCa.vaccine;

import com.example.kaninosCa.pet.Pet;
import com.example.kaninosCa.visit.Visit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Vaccine {

    @Id
    @SequenceGenerator(
            name="vaccine_sequence",
            sequenceName = "vaccine_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "vaccine_sequence",
            strategy = GenerationType.SEQUENCE
    )@Column(
            name = "id"
    )
    private Long id;



    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "pet_vaccine",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "vaccine_pet_fk"
            )
    )
    private Pet pet;

    @JsonIgnore
    @ManyToMany(
            mappedBy = "vaccines"
    )
    private List<Visit> visits = new ArrayList<>();

    private String name;
    private String description;

}
