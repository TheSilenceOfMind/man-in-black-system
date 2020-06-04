package com.itmo.mibsystem.service;

import org.springframework.data.repository.CrudRepository;

/**
 * @author BaladKV
 * @since 04.06.2020
 */
public interface RulesBasedRepositoryManager {

    CrudRepository<?, Long> getRepository(Long roleId) throws Exception;

}
