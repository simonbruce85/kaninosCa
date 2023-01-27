package com.example.kaninosCa.document;

import com.example.kaninosCa.owner.Owner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/documents")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    public List<Document> getAllDocuments() {return documentService.getAllDocuments();}

//    @GetMapping
//    public List<Owner> getOwnersPagination() {return ownerService.getAllOwners();}

    @GetMapping(path="/pets/{pet}/{id}")
    public Owner getOnedocument(@PathVariable("id") Long id){return ownerService.getOwnerById(id);}

    @GetMapping(path="/ownersDropdown")
    public List<Object> findOwnerIdAndName() {return ownerService.getAllOwnersIdAndName();}

    @PostMapping
    public void addOwner(@Valid @RequestBody Owner owner){
        ownerService.addOwner(owner);
    }
}
