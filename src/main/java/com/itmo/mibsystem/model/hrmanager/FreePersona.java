package com.itmo.mibsystem.model.hrmanager;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Random;
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

    final Random random = new Random();

    public FreePersona(Long freePersonaId, String name, String age, String profession, String education, String description) {
        this.freePersonaId = freePersonaId;
        this.name = name;
        this.age = age;
        this.profession = profession;
        this.education = education;
        this.description = description;
    }

    public FreePersona() {
        this.freePersonaId = null;
        this.name = "";
        this.age = "";
        this.profession = "";
        this.education = "";
        this.description = "";
    }

    public FreePersona(Long freePersonaId) {
        String[] alph = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z", " "};
        this.freePersonaId = freePersonaId;
        for (int i = 0; i < 3 + random.nextInt(7); i++) {
            name += alph[random.nextInt(26)];
        }
        age = (20 + random.nextInt(20)) + " age";
        for (int i = 0; i < 5 + random.nextInt(10); i++) {
            profession += alph[random.nextInt(27)];
        }
        switch (random.nextInt(2)) {
            case 0:
                education = "secondary";
                break;
            case 1:
                education = "higher";
                break;
        }
        for (int i = 0; i < 5 + random.nextInt(10); i++) {
            description += alph[random.nextInt(27)];
        }
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
