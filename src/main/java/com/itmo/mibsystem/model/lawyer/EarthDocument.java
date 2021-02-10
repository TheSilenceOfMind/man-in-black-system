package com.itmo.mibsystem.model.lawyer;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_earth_document")
@Getter
@Setter
public class EarthDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "earth_document_id")
    private Long earthDocumentId;
    @Column(name = "earth_alien_name")
    private String earthName;
    @Column(name = "id_nation")
    private Long idNation;
    @Column(name = "id_type_document")
    private Long idTypeDocument;
    @Column(name = "id_alien")
    private Long idAlien;
    @Column(name = "description")
    private String description;
    @Transient
    private String nameAlien;
    @Transient
    private String nameNation;
    @Transient
    private String nameTypeDocument;

    public EarthDocument() {
        earthDocumentId = null;
        earthName = "";
        idNation = 0L;
        idTypeDocument = 0L;
        idAlien = 0L;
        description = "";
    }

    public EarthDocument(String earthName, Long idNation, Long idTypeDocument, Long idAlien, String description) {
        earthDocumentId = null;
        this.earthName = earthName;
        this.idNation = idNation;
        this.idTypeDocument = idTypeDocument;
        this.idAlien = idAlien;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EarthDocument document = (EarthDocument) o;
        return Objects.equal(earthDocumentId, document.earthDocumentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(earthDocumentId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EarthDocument.class.getSimpleName() + "[", "]")
                .add("id=" + earthDocumentId)
                .add("earth_alien_name=" + earthName)
                .add("id_nation=" + idNation)
                .add("id_type_document=" + idTypeDocument)
                .add("id_alien=" + idAlien)
                .add("description=" + description)
                .toString();
    }

}
