package com.example.kaninosCa.document;

import com.example.kaninosCa.exception.BadRequestException;
import com.example.kaninosCa.owner.Owner;
import com.example.kaninosCa.owner.OwnerRepository;
import com.example.kaninosCa.vaccine.Vaccine;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public List<Document> getAllDocuments(){return documentRepository.findAll();}

    @PostMapping
    public void addDocument(Document document) {
        documentRepository.save(document);
    }



}
