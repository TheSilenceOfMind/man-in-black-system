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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "use")
    private String use;
    @Column(name = "description")
    private String description;
    @Column(name = "id_race")
    private Long id_race;
    @Column(name = "id_source")
    private Long id_source;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Technology document = (Technology) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Technology.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name=" + name)
                .add("use=" + use)
                .add("id_race=" + id_race)
                .add("id_source=" + id_source)
                .toString();
    }
}
