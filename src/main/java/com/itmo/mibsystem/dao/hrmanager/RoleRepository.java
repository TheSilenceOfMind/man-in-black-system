package com.itmo.mibsystem.dao.hrmanager;

import com.itmo.mibsystem.model.Role;

import java.util.List;
import java.util.Optional;

import com.itmo.mibsystem.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);

}