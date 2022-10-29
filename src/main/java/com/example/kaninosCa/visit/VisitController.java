package com.example.kaninosCa.visit;

import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.pet.Pet;
import com.example.kaninosCa.pet.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/visit")
@AllArgsConstructor
public class VisitController {
    private final VisitService visitService;
    private final PetService petService;

    @GetMapping
    public List<Visit> getAllVisits() {return visitService.getAllVisits();}

    @PostMapping
    public void addVisit(@RequestBody Visit visit){
        Pet pet = petService.getPetById(visit.getPetId());
        pet.addVisit(visit);
        visitService.addVisit(visit);
    }

    @DeleteMapping(path="{visitId}")
    public void deleteVisit(
            @PathVariable("visitId") Long visitId){
        visitService.deleteVisit(visitId);
    }
}
