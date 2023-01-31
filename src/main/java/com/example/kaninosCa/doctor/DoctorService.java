package com.example.kaninosCa.doctor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors(){return doctorRepository.findAll();}

    public Doctor getDoctorById(Long doctorId){ return doctorRepository.findById(doctorId).get();}

    @PostMapping
    public void addDoctor(Doctor doctor) {
        //check if email is taken
        //throw an error
        doctorRepository.save(doctor);
    }

    public  void deleteDoctor(Long doctorId){
        doctorRepository.deleteById(doctorId);
    }

    public long countDoctor(){
        return doctorRepository.count();
    }
}

