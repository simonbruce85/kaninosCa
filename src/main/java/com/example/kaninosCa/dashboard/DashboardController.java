package com.example.kaninosCa.dashboard;

import com.example.kaninosCa.doctor.DoctorService;
import com.example.kaninosCa.owner.OwnerService;
import com.example.kaninosCa.pet.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/dashboard")
@AllArgsConstructor
public class DashboardController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final DoctorService doctorService;


    @GetMapping
    public String dogCount() {
        Long countDoctor = doctorService.countDoctor();
        Long countOwner = ownerService.countOwner();
        Long countPets = petService.countAll();
        Long countDog = petService.countDogs();
        Long countCat = petService.countCats();
        Long countOthers = countPets-countDog-countCat;
        Dashboard dashboard = new Dashboard(countDoctor,countOwner,countPets,countDog,countCat,countOthers);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(dashboard);
        return jsonStr;
    }




}
