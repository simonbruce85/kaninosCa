package com.example.kaninosCa.document;

import com.example.kaninosCa.pet.Pet;
import com.example.kaninosCa.visit.Visit;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Document {
    @Id
    @SequenceGenerator(
            name="doc_sequence",
            sequenceName = "doc_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "doc_sequence",
            strategy = GenerationType.SEQUENCE
    )@Column(
            name = "id"
    )
    private Long id;
    private String documentLink;
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "pet_document",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "document_pet_fk"
            )
    )
    private Pet pet;

    @ManyToOne
    @JoinColumn(
            name = "visit_document",
            referencedColumnName = "visitId",
            foreignKey = @ForeignKey(
                    name = "document_visit_fk"
            )
    )
    private Visit visit;
}
