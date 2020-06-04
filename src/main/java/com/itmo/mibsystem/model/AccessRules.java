package com.itmo.mibsystem.model;

import com.google.common.base.Objects;
import java.util.StringJoiner;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccessRules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long roleId;
    private String tableName;

    protected AccessRules() {
    }

    public AccessRules(Long roleId, String tableName) {
        this.roleId = roleId;
        this.tableName = tableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccessRules that = (AccessRules) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccessRules.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("roleId=" + roleId)
            .add("tableName='" + tableName + "'")
            .toString();
    }

    public Long getId() {
        return id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getTableName() {
        return tableName;
    }
}
