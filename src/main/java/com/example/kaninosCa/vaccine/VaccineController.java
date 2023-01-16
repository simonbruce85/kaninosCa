package com.example.kaninosCa.vaccine;

import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.owner.OwnerService;
import com.example.kaninosCa.pet.Pet;
import com.example.kaninosCa.pet.PetService;
import com.example.kaninosCa.visit.Visit;
import com.example.kaninosCa.visit.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vaccines")
@AllArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;
    private final VisitService visitService;
    private final PetService petService;

    @GetMapping
    public List<Vaccine> getAllVaccines() {
        return vaccineService.getAllVaccines();
    }


    @GetMapping(path="{id}")
    public Vaccine getOneVaccine(@PathVariable("id") Long vaccineId){return vaccineService.getVaccineById(vaccineId);}

    @PostMapping
    public void newVaccine(@Valid @RequestBody Vaccine vaccine){
        vaccineService.addVaccine(vaccine);
    }

    @PutMapping(path = "{petId}/{vaccineId}/visit/{visitId}")
    public void addVaccine(
            @PathVariable Long petId,
            @PathVariable Long vaccineId,
            @PathVariable Long visitId
    ){
        Pet pet = petService.getPetById(petId);
        Visit visit = visitService.getVisitById(visitId);
        Vaccine vaccine = vaccineService.getVaccineById(vaccineId);
        visit.addVaccineToVisit(vaccine);
        pet.setLastVaccine(vaccine.getId());
        visitService.addVisit(visit);
    }


    @DeleteMapping(path="{id}")
    public void deletePet(
            @PathVariable("id") Long id){
        vaccineService.deleteVaccine(id);
    }
}
