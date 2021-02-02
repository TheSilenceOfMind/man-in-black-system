package com.itmo.mibsystem.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mib_user", schema="public")
@Setter
@Getter
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String username;
    private String password;
    private boolean disabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mib_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles;

    public User() {
    }

    public User(long userId, String username, String password, boolean disabled,
        boolean accountExpired,
        boolean accountLocked, boolean credentialsExpired,
        List<Role> roles) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.disabled = disabled;
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.roles = roles;
    }

    public User(long userId, String username, String password, boolean credentialsExpired) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.disabled = false;
        this.accountExpired = false;
        this.accountLocked = false;
        this.credentialsExpired = credentialsExpired;
    }
}
