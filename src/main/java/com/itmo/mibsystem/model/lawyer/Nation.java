package com.itmo.mibsystem.model.lawyer;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_nation")
@Getter
@Setter
public class Nation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nation_id")
    private Long nationId;
    @Column(name = "name")
    private String name;

    public Nation() {
        nationId = null;
        name = "";
    }

    public Nation(String name) {
        nationId = null;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Nation document = (Nation) o;
        return Objects.equal(nationId, document.nationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nationId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Nation.class.getSimpleName() + "[", "]")
                .add("nation_id=" + nationId)
                .add("name=" + name)
                .toString();
    }
}
