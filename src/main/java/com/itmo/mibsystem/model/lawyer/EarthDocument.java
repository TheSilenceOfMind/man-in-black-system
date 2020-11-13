package com.itmo.mibsystem.model.lawyer;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_earth_documents")
@Getter
@Setter
public class EarthDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EarthDocument document = (EarthDocument) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EarthDocument.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("earth_alien_name=" + earthName)
                .add("id_nation=" + idNation)
                .add("id_type_document=" + idTypeDocument)
                .add("id_alien=" + idAlien)
                .add("description=" + description)
                .toString();
    }

}
