package com.itmo.mibsystem.model.hrmanager;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_free_personnel")
@Getter
@Setter
public class FreePersona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;
    @Column(name = "profession")
    private String profession;
    @Column(name = "education")
    private String education;
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
        FreePersona document = (FreePersona) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FreePersona.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name=" + name)
                .add("age=" + age)
                .add("profession=" + profession)
                .add("education=" + education)
                .add("description=" + description)
                .toString();
    }
}
