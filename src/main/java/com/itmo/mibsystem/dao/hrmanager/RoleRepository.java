package com.itmo.mibsystem.dao.hrmanager;

import com.itmo.mibsystem.model.Role;

import java.util.List;
import java.util.Optional;

import com.itmo.mibsystem.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);

    @Query("SELECT r FROM Role r WHERE r.roleId = :roleId")
    List<Role> findRoleByRoleId(@Param("roleId") int roleId);

    @Transactional
    @Modifying
    @Query("delete from Role r where r.roleId = :roleId")
    void deleteByRoleId(@Param("roleId") int roleId);
}