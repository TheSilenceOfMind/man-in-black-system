package com.itmo.mibsystem.model.lawyer;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_type_earth_documents")
@Getter
@Setter
public class TypeEarthDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "type")
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeEarthDocument document = (TypeEarthDocument) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TypeEarthDocument.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type=" + type)
                .toString();
    }
}
