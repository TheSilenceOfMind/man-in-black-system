package com.itmo.mibsystem.dao;

import com.itmo.mibsystem.model.AccessRules;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author BaladKV
 * @since 04.06.2020
 */
@Repository
public interface AccessRulesRepository extends CrudRepository<AccessRules, Long> {
}
