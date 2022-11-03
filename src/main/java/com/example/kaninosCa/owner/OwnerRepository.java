package com.example.kaninosCa.owner;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    @Query("select p.id, p.name from Owner p")
    List<Object> getAllIdsAndNames();

    @Query("" + " SELECT CASE WHEN COUNT(s) > 0 THEN " + " TRUE ELSE FALSE END " + "FROM Owner s " + " WHERE s.phone = ?1 ")
    Boolean selectExistsPhone(String phone);
}
