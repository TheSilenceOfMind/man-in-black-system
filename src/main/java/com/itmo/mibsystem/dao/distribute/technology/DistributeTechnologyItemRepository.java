package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.DistributeTechnologyItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface DistributeTechnologyItemRepository extends CrudRepository<DistributeTechnologyItem, Long> {
    Optional<User> findByUsername(String username);
}