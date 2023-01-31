package com.example.kaninosCa.document;
import com.example.kaninosCa.visit.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/documents")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final VisitService visitService;

    @GetMapping
    public List<Document> getAllDocuments() {return documentService.getAllDocuments();}


    @PostMapping
    public void addDocument(@Valid @RequestBody Document document){
        documentService.addDocument(document);
    }

    @PostMapping(path = "/pet/{id}/document/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadDocumentToPet(@PathVariable("id") Long id,
                                      @RequestParam("file") MultipartFile file){
        documentService.uploadDocumentToPet(id, file);
    }

    @GetMapping("/pet/{id}/download/{key}")
    public String getUrlDocumentFromPet(@PathVariable("id") Long id, @PathVariable("key") String key){
        return documentService.getUrlDocumentFromPet(id, key);
    }

    @PostMapping(path = "/visit/{id}/document/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadDocumentToVisit(@PathVariable("id") Long visitId,
                                    @RequestParam("file") MultipartFile file){
        Long petId = visitService.getVisitById(visitId).getPet().getId();
        documentService.uploadDocumentToVisit(petId, visitId, file);
    }

    @GetMapping("/visit/{id}/download/{key}")
    public String getUrlDocumentFromVisit(@PathVariable("id") Long visitId, @PathVariable("key") String key){
        Long petId = visitService.getVisitById(visitId).getPet().getId();
        return documentService.getUrlDocumentFromVisit(visitId, petId, key);
    }

    //method to test if I can show the file on the website without downloading it.
//    @GetMapping("/pets/{id}/downloadOK/{key}")
//    public byte[] downloadDocumentFromPet(@PathVariable("id") Long id, @PathVariable("key") String key){
//        return documentService.downloadDocumentFromPet(id,key);
//    }

}
