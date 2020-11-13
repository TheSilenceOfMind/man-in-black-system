package com.itmo.mibsystem.dao.pasportist;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.pasportist.AlienPassport;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlienPassportRepository extends CrudRepository<AlienPassport, Long> {
    Optional<User> findByUsername(String username);
}