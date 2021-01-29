package com.itmo.mibsystem.model.researcher;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_technology")
@Getter
@Setter
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_id")
    private Long technologyId;
    @Column(name = "name")
    private String name;
    @Column(name = "use")
    private String use;
    @Column(name = "description")
    private String description;
    @Column(name = "id_race")
    private Long idRace;
    @Column(name = "id_source")
    private Long idSource;
    @Transient
    private String valueSourse;
    @Transient
    private String nameRace;

    public Technology() {
        this.technologyId = null;
        this.name = "";
        this.use = "";
        this.description = "";
        this.idRace = 0L;
        this.idSource = 0L;
    }

    public Technology(String name, String use, String description, Long idRace, Long idSource) {
        this.technologyId = null;
        this.name = name;
        this.use = use;
        this.description = description;
        this.idRace = idRace;
        this.idSource = idSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Technology document = (Technology) o;
        return Objects.equal(technologyId, document.technologyId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(technologyId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Technology.class.getSimpleName() + "[", "]")
                .add("id=" + technologyId)
                .add("name=" + name)
                .add("use=" + use)
                .add("id_race=" + idRace)
                .add("id_source=" + idSource)
                .toString();
    }
}
