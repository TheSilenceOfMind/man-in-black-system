package com.itmo.mibsystem.dao.operative;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.operative.ActDetention;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ActDetentionRepository extends CrudRepository<ActDetention, Long> {
    Optional<User> findByUsername(String username);
}