package com.itmo.mibsystem.dao.hrmanager;

import com.itmo.mibsystem.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * @author BaladKV
 * @since 08.11.2020
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
