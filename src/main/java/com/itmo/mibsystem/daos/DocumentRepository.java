package com.itmo.mibsystem.daos;

import com.itmo.mibsystem.model.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author BaladKV
 * @since 04.06.2020
 */
@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

}
