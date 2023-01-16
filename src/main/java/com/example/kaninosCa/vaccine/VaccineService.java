package com.example.kaninosCa.vaccine;

import com.example.kaninosCa.pet.Pet;
import com.example.kaninosCa.visit.Visit;
import com.example.kaninosCa.visit.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Service
public class VaccineService {


    private final VaccineRepository vaccineRepository;

    public List<Vaccine> getAllVaccines(){return vaccineRepository.findAll();}

    @PostMapping
    public void addVaccine(Vaccine vaccine) {
        vaccineRepository.save(vaccine);
    }

    public  void deleteVaccine(Long vaccineId){
        vaccineRepository.deleteById(vaccineId);
    }

    public Vaccine getVaccineById(Long id){ return vaccineRepository.findById(id).get();}
}
