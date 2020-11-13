package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_distribute_technology_items")
@Getter
@Setter
public class DistributeTechnologyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "id_technology")
    private Long idTechnology;
    @Column(name = "id_agent")
    private Long idAgent;
    @Column(name = "count")
    private Long count;
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
        DistributeTechnologyItem document = (DistributeTechnologyItem) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DistributeTechnologyItem.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("id_technology=" + idTechnology)
                .add("id_agent=" + idAgent)
                .add("count=" + count)
                .add("description=" + description)
                .toString();
    }
}
