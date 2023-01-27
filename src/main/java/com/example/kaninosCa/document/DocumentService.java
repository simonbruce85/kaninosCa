package com.example.kaninosCa.document;

import com.example.kaninosCa.bucket.BucketName;
import com.example.kaninosCa.config.filestore.FileStore;
import com.example.kaninosCa.exception.BadRequestException;
import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.owner.OwnerRepository;
import com.example.kaninosCa.pet.Pet;
import com.example.kaninosCa.pet.PetRepository;
import com.example.kaninosCa.vaccine.Vaccine;
import com.example.kaninosCa.visit.Visit;
import com.example.kaninosCa.visit.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@AllArgsConstructor
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final PetRepository petRepository;

    private final VisitRepository visitRepository;
    private final FileStore fileStore;

    public List<Document> getAllDocuments(){return documentRepository.findAll();}

    @PostMapping
    public void addDocument(Document document) {
        documentRepository.save(document);
    }

    public void uploadDocumentToPet(Long id, MultipartFile file) {
        //check if image is not empty
        if (file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + " ]");
        }
        //check if file is an image
//        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())){
//            throw new IllegalStateException("File must be an image");
//        }

        //check if user exists
        Pet petSelected = petRepository.findAll().stream().filter(pet-> pet.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", id)));

        //Grab metadata from the file
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        //path format
        String path = String.format("%s/pets/%s/documents", BucketName.BUCKET_NAME.getBucketName(), id);
        String filename = String.format("%s-%s", UUID.randomUUID(), file.getOriginalFilename());

        try {
            fileStore.save(path,filename,Optional.of(metadata),file.getInputStream());
            //save pet with new profile image link
            Document document = new Document();
            document.setDocumentLink(filename);
            document.setName(file.getOriginalFilename());
            Pet pet = petRepository.findById(id).get();
            pet.addDocument(document);
            documentRepository.save(document);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    String getUrlDocumentFromPet(Long id, String key){
        String path = String.format("pets/%s/documents/%s", id,key);
        return fileStore.getURL(path);
    }

    public void uploadDocumentToVisit(Long petId, Long visitId, MultipartFile file) {
        //check if image is not empty
        if (file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + " ]");
        }
        //check if file is an image
//        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())){
//            throw new IllegalStateException("File must be an image");
//        }

        //check if user exists
        Pet petSelected = petRepository.findAll().stream().filter(pet-> pet.getId().equals(petId)).findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Pet %s not found", petId)));

        //Grab metadata from the file
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        //path format
        String path = String.format("%s/pets/%s/visit/%s/documents", BucketName.BUCKET_NAME.getBucketName(), petId,visitId);
        String filename = String.format("%s-%s", UUID.randomUUID(), file.getOriginalFilename());

        try {
            fileStore.save(path,filename,Optional.of(metadata),file.getInputStream());
            //save pet with new profile image link
            Document document = new Document();
            document.setDocumentLink(filename);
            document.setName(file.getOriginalFilename());
            Visit visit = visitRepository.findById(visitId).get();
            visit.addDocument(document);
            documentRepository.save(document);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    String getUrlDocumentFromVisit(Long petId, Long visitId, String key){
        String path = String.format("pets/%s/visit/%s/documents/%s", petId,visitId,key);
        return fileStore.getURL(path);
    }

//    byte[] downloadDocumentFromPet(Long id, String key){
//        String path = String.format("%s/pets/%s/documents", BucketName.BUCKET_NAME.getBucketName(), id);
//        return fileStore.download(path, key);
//    }


}
