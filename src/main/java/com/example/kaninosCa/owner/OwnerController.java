package com.example.kaninosCa.owner;

import com.example.kaninosCa.doctor.Doctor;
import com.example.kaninosCa.pet.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/owners")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    public List<Owner> getAllOwners() {return ownerService.getAllOwners();}

//    @GetMapping
//    public List<Owner> getOwnersPagination() {return ownerService.getAllOwners();}

    @GetMapping(path="{id}")
    public Owner getOneOwner(@PathVariable("id") Long id){return ownerService.getOwnerById(id);}

    @GetMapping(path="/ownersDropdown")
    public List<Object> findOwnerIdAndName() {return ownerService.getAllOwnersIdAndName();}

    @PostMapping
    public void addOwner(@Valid @RequestBody Owner owner){
        throw new IllegalStateException("oops eroorr");
    }

    @DeleteMapping(path="{id}")
    public void deleteOwner(
            @PathVariable("id") Long id){
        ownerService.deleteOwner(id);
    }




}
