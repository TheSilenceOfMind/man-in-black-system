package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_technology_market_items")
@Getter
@Setter
public class TechnologyMarketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "id_technology")
    private Long idTechnology;
    @Column(name = "use")
    private String use;
    @Column(name = "id_alien_race")
    private Long id_alien_race;
    @Column(name = "count")
    private Long count;
    @Column(name = "id_delivery_type")
    private Long idDeliveryType;
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
        TechnologyMarketItem document = (TechnologyMarketItem) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TechnologyMarketItem.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("id_technology=" + idTechnology)
                .add("use=" + use)
                .add("id_alien_race=" + id_alien_race)
                .add("count=" + count)
                .add("id_delivery_type=" + idDeliveryType)
                .add("description=" + description)
                .toString();
    }
}
