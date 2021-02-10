package com.itmo.mibsystem.dao.hrmanager;

import com.itmo.mibsystem.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author BaladKV
 * @since 08.11.2020
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u join u.roles r where r.roleName = :roleName order by u.userId ASC")
    List<User> findAllByRoles(String roleName);

    @Query("SELECT u FROM User u join u.roles r where u.userId = :userId order by u.userId ASC")
    List<User> findUserByUserId(Long userId);

    void deleteByUsername(String username);
}
