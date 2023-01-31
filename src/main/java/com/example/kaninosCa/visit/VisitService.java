package com.example.kaninosCa.visit;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public List<Visit> getAllVisits(){return visitRepository.findAll();}

    @PostMapping
    public void addVisit(Visit visit) {
        //throw an error
        visitRepository.save(visit);
    }

    public  void deleteVisit(Long visitId){
        visitRepository.deleteById(visitId);
    }

    public Visit getVisitById(Long visitId){
        return visitRepository.findById(visitId).get();
    }
}
