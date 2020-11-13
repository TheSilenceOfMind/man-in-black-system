package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.DeliveryType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeliveryTypeRepository extends CrudRepository<DeliveryType, Long> {
    Optional<User> findByUsername(String username);
}