package com.itmo.mibsystem.model.passporter;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_alien_passport")
@Getter
@Setter
public class AlienPassport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    private Long passportId;
    @Column(name = "name")
    private String name;
    @Column(name = "home_planet")
    private String homePlanet;
    @Column(name = "description")
    private String description;
    @Column(name = "id_race")
    private Long idRace;
    @Transient
    private String nameRace;

    public AlienPassport() {
        this.passportId = null;
        this.name = "";
        this.homePlanet = "";
        this.description = "";
        this.idRace = 0L;
    }

    public AlienPassport(Long passportId, String name, String homePlanet, String description, Long idRace, String nameRace) {
        this.passportId = passportId;
        this.name = name;
        this.homePlanet = homePlanet;
        this.description = description;
        this.idRace = idRace;
        this.nameRace = nameRace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AlienPassport document = (AlienPassport) o;
        return Objects.equal(passportId, document.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(passportId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlienPassport.class.getSimpleName() + "[", "]")
                .add("passport_id=" + passportId)
                .add("name=" + name)
                .add("home_planet=" + homePlanet)
                .add("id_race=" + idRace)
                .add("description=" + description)
                .toString();
    }
}
