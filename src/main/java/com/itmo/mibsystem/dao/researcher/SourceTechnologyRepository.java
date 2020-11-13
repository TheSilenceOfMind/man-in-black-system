package com.itmo.mibsystem.dao.researcher;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.researcher.SourceTechnology;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SourceTechnologyRepository extends CrudRepository<SourceTechnology, Long> {
    Optional<User> findByUsername(String username);
}