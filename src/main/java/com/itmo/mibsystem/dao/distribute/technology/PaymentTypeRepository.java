package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.PaymentType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {
    Optional<User> findByUsername(String username);
}