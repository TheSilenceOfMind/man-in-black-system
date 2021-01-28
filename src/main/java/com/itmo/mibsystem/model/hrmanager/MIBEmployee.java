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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mib_employee_id")
    private Long MIBEmployeeId;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private String age;
    @Column(name = "id_curator")
    private Long idCurator;
    @Column(name = "description")
    private String description;
    @Transient
    private Long idRole;
    @Transient
    private String roleName;
    @Transient
    private String curatorName;
    @Transient
    private String username;
    @Transient
    private String password;
    @Transient
    private Long idFreePeople;

    public MIBEmployee() {
        MIBEmployeeId = null;
        name = "";
        age = "";
        description = "";
        idCurator = 0L;
        idUser = 0L;
        idRole = 0L;
        username = "";
        password = "";
        idFreePeople = 0L;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MIBEmployee document = (MIBEmployee) o;
        return Objects.equal(MIBEmployeeId, document.MIBEmployeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(MIBEmployeeId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MIBEmployee.class.getSimpleName() + "[", "]")
                .add("mib_employee_id=" + MIBEmployeeId)
                .add("id_user=" + idUser)
                .add("name=" + name)
                .add("age=" + age)
                .add("id_curator=" + idCurator)
                .add("description=" + description)
                .toString();
    }
}
