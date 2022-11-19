package com.example.kaninosCa.pet;

import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.visit.Visit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "owner_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "pet_owner_fk"
            )
    )
    private Owner owner;

    @OneToMany(
            mappedBy = "pet",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Visit> visits = new ArrayList<>();

    private Long ownerIndicator;
    private String name;
    private String type;
    private String breed;
    private String color;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetGender gender;
    private String weight;
    private String dob;
    private String vaccines;
    private String notes;


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

    public List<Visit> getVisits() {
        return visits;
    }

}
