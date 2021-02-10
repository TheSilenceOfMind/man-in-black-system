package com.itmo.mibsystem.dao.lawyer;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.lawyer.TypeEarthDocument;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeEarthDocumentRepository extends CrudRepository<TypeEarthDocument, Long> {
    Optional<TypeEarthDocument> findByType(String type);


}