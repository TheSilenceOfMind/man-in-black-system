package com.itmo.mibsystem.daos;

import com.itmo.mibsystem.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author BaladKV
 * @since 04.06.2020
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsernameStartsWithIgnoreCase(String username);

}
