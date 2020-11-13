package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.BuyTechnologyDocument;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BuyTechnologyDocumentRepository extends CrudRepository<BuyTechnologyDocument, Long> {
    Optional<User> findByUsername(String username);
}