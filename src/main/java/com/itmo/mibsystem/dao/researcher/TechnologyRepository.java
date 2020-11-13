package com.itmo.mibsystem.dao.researcher;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.researcher.Technology;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TechnologyRepository extends CrudRepository<Technology, Long> {
    Optional<User> findByUsername(String username);
}