package com.example.kaninosCa.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {

    @Query("SELECT COUNT(p) FROM Pet p WHERE p.type=:pet")
    Long countPet(String pet);
    //or can be done as  public List<Pet> findByType(String name) and the use .size() in service class as done with otherCount;

    public List<Pet> findPetByOwnerIndicator(Long ownerIndicator);


    public List<Pet> findByType(String name);
    public List<Pet> findByTypeNot(String name);

    @Query("" + "SELECT CASE WHEN COUNT(s) > 0 THEN " + "TRUE ELSE FALSE END " + "FROM Owner s " + "WHERE s.phone = ?1 ")
    Boolean selectExistsName(String name);



}
