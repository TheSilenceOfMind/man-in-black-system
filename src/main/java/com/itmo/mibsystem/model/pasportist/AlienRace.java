package com.itmo.mibsystem.model.pasportist;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mib_alien_race")
@Getter
@Setter
public class AlienRace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AlienRace document = (AlienRace) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlienRace.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name=" + name)
                .toString();
    }
}
