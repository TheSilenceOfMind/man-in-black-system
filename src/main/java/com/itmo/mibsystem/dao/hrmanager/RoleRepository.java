package com.itmo.mibsystem.dao.hrmanager;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.hrmanager.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<User> findByUsername(String username);
}