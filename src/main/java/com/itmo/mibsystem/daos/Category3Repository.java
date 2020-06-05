package com.itmo.mibsystem.daos;

import com.itmo.mibsystem.model.Category3;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author NoskovFA
 * @since 05.06.2020
 */
@Repository
public interface Category3Repository extends CrudRepository<Category3, Long> {
}
