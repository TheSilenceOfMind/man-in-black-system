package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.SellTechnologyDocument;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SellTechnologyDocumentRepository extends CrudRepository<SellTechnologyDocument, Long> {
    Optional<User> findByUsername(String username);
}