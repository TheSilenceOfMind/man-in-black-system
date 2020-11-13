package com.itmo.mibsystem.model.pasportist;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_alien_passports")
@Getter
@Setter
public class AlienPassport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "home_planet")
    private String homePlanet;
    @Column(name = "description")
    private String description;
    @Column(name = "id_race")
    private Long idRace;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AlienPassport document = (AlienPassport) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlienPassport.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name=" + name)
                .add("home_planet=" + homePlanet)
                .add("id_race=" + idRace)
                .add("description=" + description)
                .toString();
    }
}
