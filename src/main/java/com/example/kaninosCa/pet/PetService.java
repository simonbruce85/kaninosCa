package com.example.kaninosCa.pet;

import com.example.kaninosCa.bucket.BucketName;
import com.example.kaninosCa.config.filestore.FileStore;
import com.example.kaninosCa.doctor.DoctorRepository;
import com.example.kaninosCa.doctor.Doctor;
import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.owner.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@AllArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;

    private final DoctorRepository doctorRepository;
    private final OwnerRepository ownerRepository;

    private final FileStore fileStore;

    public List<Pet> getAllPets()
    {return petRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));}

    @PostMapping
    public void addPet(Pet pet) {
        //check if email is taken
        //throw an error
        petRepository.save(pet);
    }

    public void deletePet(Long id){
        petRepository.deleteById(id);
    }

    public Pet getPetById(Long id){ return petRepository.findById(id).get();}
    public Doctor getDoctorById(Long doctorId){ return doctorRepository.findById(doctorId).get();}

    public Owner getOwnerById(Long id){ return ownerRepository.findById(id).get();}

    public Pet saveDoctorToPet(Pet pet){
        return petRepository.save(pet);
    }

    public Pet savePetToOwner(Pet pet){
        return petRepository.save(pet);
    }
    public long countDogs(){
        return petRepository.countPet("dog");
    }

    public long countCats(){
        return petRepository.countPet("cat");
    }

    public long countAll(){
        return petRepository.count();
    }

    public List<Pet> getAllPetsOfAnOwner(Long ownerId){return petRepository.findPetByOwner(ownerId);}


    public void uploadPetProfileImage(Long id, MultipartFile file) {
        //check if image is not empty
        if (file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + " ]");
        }
        //check if file is an image
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image");
        }

        //check if user exists
        Pet petSelected = petRepository.findAll().stream().filter(pet-> pet.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", id)));

        //Grab metadata from the file
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        //path format
        String path = String.format("%s/Pets/%s/profileImage", BucketName.PROFILE_IMAGE.getBucketName(), id);
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path,filename,Optional.of(metadata),file.getInputStream());
//          //save pet with new profile image link
            Pet pet = getPetById(id);
            pet.setPetProfileImageLink(filename);
            petRepository.save(pet);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    byte[] downloadProfileImage(Long id){
        Pet pet = getPetById(id);
        String path = String.format("%s/Pets/%s/profileImage", BucketName.PROFILE_IMAGE.getBucketName(), id);
        return fileStore.download(path, pet.getPetProfileImageLink());
    }
}
