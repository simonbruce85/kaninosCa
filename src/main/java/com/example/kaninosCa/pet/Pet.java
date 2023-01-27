package com.example.kaninosCa.pet;

import com.example.kaninosCa.doctor.Doctor;
import com.example.kaninosCa.document.Document;
import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.vaccine.Vaccine;
import com.example.kaninosCa.visit.Visit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.mapping.Array;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@TypeDefs({
        @TypeDef(
                name = "list-array",
                typeClass = ListArrayType.class
        )
})
public class Pet {

    @Id
    @SequenceGenerator(
            name="pet_sequence",
            sequenceName = "pet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "pet_sequence",
            strategy = GenerationType.SEQUENCE
    )@Column(
            name = "id"
    )
    private Long id;


    @JsonIgnoreProperties({"pets"})//ignoring only the pets property in order to avoid infinite loops
    @ManyToOne
    @JoinColumn(
            name = "owner_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "pet_owner_fk"
            )
    )
    private Owner owner;

    @JsonIgnoreProperties({"pets"})//ignoring only the pets property in order to avoid infinite loops
    @ManyToOne
    @JoinColumn(
            name = "doctor_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "pet_owner_fk"
            )
    )
    private Doctor doctor;

    @OneToMany(
            mappedBy = "pet",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Visit> visits = new ArrayList<>();

    @OneToMany(
            mappedBy = "pet",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Vaccine> vaccines = new ArrayList<>();

    @JsonIgnoreProperties({"pets"})//ignoring only the pets property in order to avoid infinite loops
    @OneToMany(
            mappedBy = "pet",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Document> documents = new ArrayList<>();

    private String name;
    private String type;
    private String breed;
    private String color;

    private String petProfileImageLink;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetGender gender;
    private String weight;
    private String dob;
    @Column(columnDefinition="TEXT")
    private String notes;
    private LocalDate lastKc;
    private LocalDate lastRabia;
    private LocalDate lastSextuple;
    private LocalDate lastTriple;
    private LocalDate lastParv;
    private LocalDate lastQuint;
    private LocalDate lastGiardia;
    private LocalDate lastParvMoquillo;


    public void addVisit(Visit visit) {
        if (!this.visits.contains(visit)) {
            this.visits.add(visit);
            visit.setPet(this);
        }
    }

    public void removeVisit(Visit visit) {
        if (this.visits.contains(visit)) {
            this.visits.remove(visit);
        }
        visit.setPet(null);
    }

    public void addDocument(Document document) {
        if (!this.documents.contains(document)) {
            this.documents.add(document);
            document.setPet(this);
        }
    }

    public void removeDocument(Document document) {
        if (this.documents.contains(document)) {
            this.documents.remove(document);
        }
        document.setPet(null);
    }

    public void addVaccine(Vaccine vaccine) {
        if (!this.vaccines.contains(vaccine)) {
            this.vaccines.add(vaccine);

        }
    }



    public List<Visit> getVisits() {
        return visits;
    }

    public void setLastVaccine(Long vaccineId) {
        if (vaccineId == 1){
            this.lastKc = java.time.LocalDate.now();
        }else if(vaccineId == 2){
            this.lastRabia = java.time.LocalDate.now();
        }else if(vaccineId == 3){
            this.lastSextuple = java.time.LocalDate.now();
        }else if(vaccineId == 4){
            this.lastTriple = java.time.LocalDate.now();
        }else if(vaccineId == 5){
            this.lastParv = java.time.LocalDate.now();
        }else if(vaccineId == 6){
            this.lastQuint = java.time.LocalDate.now();
        } else if(vaccineId == 7){
            this.lastGiardia = java.time.LocalDate.now();
        }else if(vaccineId == 8){
            this.lastParvMoquillo = java.time.LocalDate.now();
        }
    }
}
