package com.itmo.mibsystem.daos;

import com.itmo.mibsystem.model.Category2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author NoskovFA
 * @since 05.06.2020
 */
@Repository
public interface Category2Repository extends CrudRepository<Category2, Long> {
}
