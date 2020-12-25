package com.itmo.mibsystem.dao.lawyer;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.lawyer.Nation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NationRepository extends CrudRepository<Nation, Long> {
    Optional<Nation> findAllByName(String name);
}