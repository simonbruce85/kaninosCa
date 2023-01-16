package com.example.kaninosCa.doctor;

import com.example.kaninosCa.pet.Pet;
import com.example.kaninosCa.pet.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/doctor")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {return doctorService.getAllDoctors();}

    @GetMapping(path="{doctorId}")
    public Doctor getOneDoctor(@PathVariable("doctorId") Long doctorId){return doctorService.getDoctorById(doctorId);}

    @PostMapping
    public void addDoctor(@RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
    }

    @DeleteMapping(path="{doctorId}")
    public void deleteDoctor(
            @PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctor(doctorId);
    }
}
