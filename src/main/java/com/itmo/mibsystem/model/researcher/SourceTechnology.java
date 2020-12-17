package com.itmo.mibsystem.model.researcher;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_source_technology")
@Getter
@Setter
public class SourceTechnology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "source_id")
    private Long sourceId;
    @Column(name = "value")
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SourceTechnology document = (SourceTechnology) o;
        return Objects.equal(sourceId, document.sourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sourceId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SourceTechnology.class.getSimpleName() + "[", "]")
                .add("id=" + sourceId)
                .add("value=" + value)
                .toString();
    }
}
