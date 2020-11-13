package com.itmo.mibsystem.dao.lawyer;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.lawyer.EarthDocument;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EarthDocumentRepository extends CrudRepository<EarthDocument, Long> {
    Optional<User> findByUsername(String username);
}