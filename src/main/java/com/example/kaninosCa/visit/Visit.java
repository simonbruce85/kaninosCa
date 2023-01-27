package com.example.kaninosCa.visit;

import com.example.kaninosCa.document.Document;
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
@AllArgsConstructor
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
    @Column(columnDefinition="TEXT")
    private String visitReason;
    @Column(columnDefinition="TEXT")
    private String symptoms;
    @Column(columnDefinition="TEXT")
    private String complExams;
    @Column(columnDefinition="TEXT")
    private String diagnostic;
    @Column(columnDefinition="TEXT")
    private String clinicTreatment;
    @Column(columnDefinition="TEXT")
    private String atHomeTreatment;

    @Column(columnDefinition="TEXT")
    private String notes;


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

    @JsonIgnoreProperties({"visit"})//ignoring only the pets property in order to avoid infinite loops
    @OneToMany(
            mappedBy = "visit",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Document> documents = new ArrayList<>();

    public void addVaccineToVisit(Vaccine vaccine) {
        vaccines.add(vaccine);
        vaccine.getVisits().add(this);
    }

    public void removeVaccineFromVisit(Vaccine vaccine){
        vaccines.remove(vaccine);
        vaccine.getVisits().remove(this);
    }

    public void addDocument(Document document) {
        if (!this.documents.contains(document)) {
            this.documents.add(document);
            document.setVisit(this);
        }
    }

    public void removeDocument(Document document) {
        if (this.documents.contains(document)) {
            this.documents.remove(document);
        }
        document.setVisit(null);
    }

}
