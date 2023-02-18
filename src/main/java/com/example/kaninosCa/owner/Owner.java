package com.example.kaninosCa.owner;

import com.example.kaninosCa.pet.Pet;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
@ToString
@EqualsAndHashCode
@Setter
@Getter
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    @SequenceGenerator(
            name="owner_sequence",
            sequenceName = "owner_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "owner_sequence",
            strategy = GenerationType.SEQUENCE

    )
    @Column(
            name = "id"
    )private Long id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Column(nullable = false)
    private String address;
    @NotNull
    @Column(nullable = false)
    private String phone;

    private String email;
    @Column(columnDefinition="TEXT")
    private String notes;

    @OneToMany(
            mappedBy = "owner",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Pet> pets = new ArrayList<>();


    public void addPet(Pet pet) {
        if (!this.pets.contains(pet)) {
            this.pets.add(pet);
            pet.setOwner(this);
        }
    }

    public void removePet(Pet pet) {
        if (this.pets.contains(pet)) {
            this.pets.remove(pet);
        }
        pet.setOwner(null);
    }


    public List<Pet> getPets() {
        return pets;
    }


}
