package com.example.kaninosCa.visit;

import com.example.kaninosCa.pet.Pet;
import com.example.kaninosCa.vaccine.Vaccine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Long doctorId;
    private String date;
    private String doctor;
    private String visitReason;
    private String symptoms;
    private String diagnostic;
    private String clinicTreatment;
    private String atHomeTreatment;


    @JsonIgnoreProperties({"visits"})
    @ManyToOne
    @JoinColumn(
            name = "pet_visit",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "pet_visit_fk"
            )
    )
    private Pet pet;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "visitVaccines",
            joinColumns = @JoinColumn(
                    name = "visitId",
                    foreignKey = @ForeignKey(name = "visitVaccine_visit_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "vaccineId",
                    foreignKey = @ForeignKey(name = "visitVaccine_vaccine_id_fk")
            )

    )
    private List<Vaccine> vaccines = new ArrayList<>();

    public void addVaccineToVisit(Vaccine vaccine) {
        vaccines.add(vaccine);
        vaccine.getVisits().add(this);
    }

    public void removeVaccineFromVisit(Vaccine vaccine){
        vaccines.remove(vaccine);
        vaccine.getVisits().remove(this);
    }

    public Visit(Long visitId, Long doctorId, String date, String doctor, String visitReason, String symptoms, String diagnostic, String clinicTreatment, String atHomeTreatment, Pet pet, List<Vaccine> vaccines) {
        this.visitId = visitId;
        this.doctorId = doctorId;
        this.date = date;
        this.doctor = doctor;
        this.visitReason = visitReason;
        this.symptoms = symptoms;
        this.diagnostic = diagnostic;
        this.clinicTreatment = clinicTreatment;
        this.atHomeTreatment = atHomeTreatment;
        this.pet = pet;
        this.vaccines = vaccines;
    }
}
