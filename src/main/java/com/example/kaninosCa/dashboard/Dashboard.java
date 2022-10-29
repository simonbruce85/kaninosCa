package com.example.kaninosCa.dashboard;

import com.example.kaninosCa.doctor.Doctor;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Dashboard {

    private Long doctorCount;
    private Long ownersCount;
    private Long totalPetCount;
    private Long dogCount;
    private Long catCount;
    private Long otherCount;

    public Dashboard(Long doctorCount, Long ownersCount, Long totalPetCount, Long dogCount, Long catCount, Long otherCount) {
        this.doctorCount = doctorCount;
        this.ownersCount = ownersCount;
        this.totalPetCount = totalPetCount;
        this.dogCount = dogCount;
        this.catCount = catCount;
        this.otherCount = otherCount;
    }
}
