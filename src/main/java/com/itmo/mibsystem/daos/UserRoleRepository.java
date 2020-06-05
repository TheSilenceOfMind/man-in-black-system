package com.itmo.mibsystem.daos;

import com.itmo.mibsystem.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author NoskovFA
 * @since 04.06.2020
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
}
