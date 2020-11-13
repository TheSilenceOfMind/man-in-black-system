package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.TechnologyMarketItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TechnologyMarketItemRepository extends CrudRepository<TechnologyMarketItem, Long> {
    Optional<User> findByUsername(String username);
}