package com.itmo.mibsystem.model.op_agent;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_act_detentions")
@Getter
@Setter
public class ActDetention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "act_detention_id")
    private Long actDetentionId;
    @Column(name = "scene")
    private String scene;
    @Column(name = "id_guilty_alien")
    private Long idGuiltyAlien;
    @Column(name = "id_user_agent")
    private Long idUserAgent;
    @Column(name = "description")
    private String description;
    @Transient
    private String nameAlien;
    @Transient
    private String nameAgent;

    public ActDetention() {
        actDetentionId = null;
        scene = "";
        idGuiltyAlien = 0L;
        idUserAgent = 0L;
        description = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActDetention document = (ActDetention) o;
        return Objects.equal(actDetentionId, document.actDetentionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(actDetentionId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ActDetention.class.getSimpleName() + "[", "]")
                .add("id=" + actDetentionId)
                .add("scene=" + scene)
                .add("id_guilty_alien=" + idGuiltyAlien)
                .add("id_user_agent=" + idUserAgent)
                .add("description=" + description)
                .toString();
    }
}
