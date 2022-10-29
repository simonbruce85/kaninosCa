package com.example.kaninosCa.owner;

import com.example.kaninosCa.pet.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@Table
@NoArgsConstructor
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
    private String name;
    private String address;
    private String phone;
    private String notes;

    @OneToMany(
            mappedBy = "owner",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Pet> pets = new ArrayList<>();

    public Owner(Long id, String name, String address, String phone, String notes, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.notes = notes;
        this.pets = pets;
    }

    public void addPet(Pet pet) {
            pet.setOwner(this);
        if (!this.pets.contains(pet)) {
            this.pets.add(pet);
        }
    }

    public void removePet(Pet pet) {
        if (this.pets.contains(pet)) {
            this.pets.remove(pet);
            pet.setOwner(null);
        }
    }


    public List<Pet> getPets() {
        return pets;
    }


}
