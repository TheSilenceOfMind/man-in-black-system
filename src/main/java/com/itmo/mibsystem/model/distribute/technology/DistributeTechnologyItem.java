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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distribute_technology_item_id")
    private Long distributeTechnologyItemId;
    @Column(name = "count")
    private Long count;
    @Column(name = "use")
    private String use;
    @Column(name = "id_technology")
    private Long idTechnology;
    @Column(name = "id_agent")
    private Long idAgent;
    @Column(name = "description")
    private String description;

    @Transient
    private String technologyName;
    @Transient
    private String agentName;

    public DistributeTechnologyItem() {
        distributeTechnologyItemId = null;
        count = 0L;
        use = "";
        idTechnology = 0L;
        idAgent = 0L;
        description = "";
    }
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
                .add("count=" + count)
                .add("use=" + use)
                .add("id_technology=" + idTechnology)
                .add("id_agent=" + idAgent)
                .add("description=" + description)
                .toString();
    }
}
