package com.itmo.mibsystem.model.pasportist;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.StringJoiner;
import javax.persistence.*;

@Entity
@Table(name = "mib_alien_race")
@Getter
@Setter
public class AlienRace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "race_id")
    private Long raceId;
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
        return Objects.equal(raceId, document.raceId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(raceId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlienRace.class.getSimpleName() + "[", "]")
                .add("race_id=" + raceId)
                .add("name=" + name)
                .toString();
    }
}
