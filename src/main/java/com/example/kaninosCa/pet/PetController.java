package com.example.kaninosCa.pet;

import com.example.kaninosCa.doctor.Doctor;
import com.example.kaninosCa.doctor.DoctorService;
import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.owner.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/pets")
@AllArgsConstructor
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private DoctorService doctorService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }


    @GetMapping(path = "/dogCount")
    public Long dogCount() {
        return petService.countDogs();
    }


    @GetMapping(path="{id}")
    public Pet getOnePet(@PathVariable("id") Long petId){return petService.getPetById(petId);}

    @GetMapping(path="/allPetsOfOwner/{id}")
    public List<Pet> getAllPetsOfAnOwner(@PathVariable("id") Long id){return petService.getAllPetsOfAnOwner(id);}

    @PostMapping(path ="/owner/{ownerId}/doctor/{doctorId}" )
    public void addPet(@RequestBody Pet pet,
                       @PathVariable("ownerId") Long ownerId,
                        @PathVariable("doctorId")Long doctorId){
        Owner owner = ownerService.getOwnerById(ownerId);
        Doctor doctor = doctorService.getDoctorById(doctorId);
        owner.addPet(pet);
        doctor.addPet(pet);
        petService.addPet(pet);
    }

    @DeleteMapping(path="{id}")
    public void deletePet(
            @PathVariable("id") Long id){
        //in order to remove a pet it is necessary to unlink it from its owner first, for this reason owner.removePet() is called
        Pet pet = petService.getPetById(id);
        Owner owner = ownerService.getOwnerById(pet.getOwner().getId());
        Doctor doctor = doctorService.getDoctorById(pet.getOwner().getId());
        owner.removePet(pet);
        doctor.removePet(pet);
        petService.deletePet(id);
    }



}
