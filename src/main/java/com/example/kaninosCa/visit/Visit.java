package com.example.kaninosCa.visit;

import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.pet.Pet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table
public class Visit {

    @Id
    @SequenceGenerator(
            name="visit_sequence",
            sequenceName = "visit_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "visit_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long visitId;
    private Long petId;
    private Long doctorId;
    private String date;
    private String visitReason;
    private String symptoms;
    private String diagnostic;
    private String clinicTreatment;
    private String atHomeTreatment;
    private String vaccines;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "pet_visit",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "pet_visit_fk"
            )
    )
    private Pet pet;


    public Visit(Long visitId, Long petId, Long doctorId, String date, String visitReason, String symptoms, String diagnostic, String clinicTreatment, String atHomeTreatment, String vaccines, Pet pet) {
        this.visitId = visitId;
        this.petId = petId;
        this.doctorId = doctorId;
        this.date = date;
        this.visitReason = visitReason;
        this.symptoms = symptoms;
        this.diagnostic = diagnostic;
        this.clinicTreatment = clinicTreatment;
        this.atHomeTreatment = atHomeTreatment;
        this.vaccines = vaccines;
        this.pet = pet;
    }
}
