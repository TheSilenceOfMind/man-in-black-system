package com.itmo.mibsystem.model.hrmanager;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_employees")
@Getter
@Setter
public class MIBEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;
    @Column(name = "id_role")
    private String idRole;
    @Column(name = "id_curator")
    private Long idСurator;
    @Column(name = "description")
    private Long description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MIBEmployee document = (MIBEmployee) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MIBEmployee.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("id_user=" + idUser)
                .add("name=" + name)
                .add("age=" + age)
                .add("id_role=" + idRole)
                .add("id_curator=" + idСurator)
                .add("description=" + description)
                .toString();
    }
}
