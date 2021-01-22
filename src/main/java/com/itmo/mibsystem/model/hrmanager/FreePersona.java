package com.itmo.mibsystem.model.hrmanager;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;


@Getter
@Setter
public class FreePersona {
    private Long freePersonaId;
    private String name;
    private String age;
    private String profession;
    private String education;
    private String description;

    public FreePersona(Long freePersonaId, String name, String age, String profession, String education, String description) {
        this.freePersonaId = freePersonaId;
        this.name = name;
        this.age = age;
        this.profession = profession;
        this.education = education;
        this.description = description;
    }

    public FreePersona() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FreePersona document = (FreePersona) o;
        return Objects.equal(freePersonaId, document.freePersonaId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(freePersonaId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FreePersona.class.getSimpleName() + "[", "]")
                .add("freePersonaId=" + freePersonaId)
                .add("name=" + name)
                .add("age=" + age)
                .add("profession=" + profession)
                .add("education=" + education)
                .add("description=" + description)
                .toString();
    }
}
