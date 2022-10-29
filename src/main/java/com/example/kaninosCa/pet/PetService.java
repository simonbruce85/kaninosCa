package com.example.kaninosCa.pet;

import com.example.kaninosCa.doctor.DoctorRepository;
import com.example.kaninosCa.doctor.Doctor;
import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.owner.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;

    private final DoctorRepository doctorRepository;
    private final OwnerRepository ownerRepository;

    public List<Pet> getAllPets()
    {return petRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));}

    @PostMapping
    public void addPet(Pet pet) {
        //check if email is taken
        //throw an error
        petRepository.save(pet);
    }

    public void deletePet(Long id){
        petRepository.deleteById(id);
    }

    public Pet getPetById(Long id){ return petRepository.findById(id).get();}
    public Doctor getDoctorById(Long doctorId){ return doctorRepository.findById(doctorId).get();}

    public Owner getOwnerById(Long id){ return ownerRepository.findById(id).get();}

    public Pet saveDoctorToPet(Pet pet){
        return petRepository.save(pet);
    }

    public Pet savePetToOwner(Pet pet){
        return petRepository.save(pet);
    }
    public long countDogs(){
        return petRepository.countPet("dog");
    }

    public long countCats(){
        return petRepository.countPet("cat");
    }

    public long countAll(){
        return petRepository.count();
    }

    public List<Pet> getAllPetsOfAnOwner(Long ownerIndicator){return petRepository.findPetByOwnerIndicator(ownerIndicator);}
}
