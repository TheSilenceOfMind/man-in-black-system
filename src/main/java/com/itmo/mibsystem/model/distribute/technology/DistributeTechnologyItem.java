package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_distribute_technology_item")
@Getter
@Setter
public class DistributeTechnologyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "distribute_technology_item_id")
    private Long distributeTechnologyItemId;
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
        return Objects.equal(distributeTechnologyItemId, document.distributeTechnologyItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(distributeTechnologyItemId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DistributeTechnologyItem.class.getSimpleName() + "[", "]")
                .add("distribute_technology_item_id=" + distributeTechnologyItemId)
                .add("id_technology=" + idTechnology)
                .add("id_agent=" + idAgent)
                .add("count=" + count)
                .add("description=" + description)
                .toString();
    }
}
