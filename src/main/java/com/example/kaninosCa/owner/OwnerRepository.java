package com.example.kaninosCa.owner;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    @Query("select p.id, p.name from Owner p")
    List<Object> getAllIdsAndNames();
}
