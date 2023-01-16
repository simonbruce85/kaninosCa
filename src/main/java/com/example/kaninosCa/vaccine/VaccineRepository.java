package com.example.kaninosCa.vaccine;

import com.example.kaninosCa.visit.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Long> {
}
