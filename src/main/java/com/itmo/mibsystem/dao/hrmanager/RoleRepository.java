package com.itmo.mibsystem.dao.hrmanager;

import com.itmo.mibsystem.model.Role;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}