package com.example.kaninosCa.owner;

import com.example.kaninosCa.pet.Pet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> getAllOwners(){return ownerRepository.findAll();}

    public List<Object> getAllOwnersIdAndName(){return ownerRepository.getAllIdsAndNames();}

    @PostMapping
    public void addOwner(Owner owner) {
        //check if email is taken
        //throw an error
        ownerRepository.save(owner);
    }

    public long countOwner(){
        return ownerRepository.count();
    }

    public  void deleteOwner(Long ownerId){
        ownerRepository.deleteById(ownerId);
    }

    public Owner getOwnerById(Long ownerId){
       return ownerRepository.findById(ownerId).get();
    }

}
