package com.itmo.mibsystem.dao.hrmanager;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.hrmanager.FreePersona;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FreePersonaRepository extends CrudRepository<FreePersona, Long> {
    Optional<User> findByUsername(String username);
}