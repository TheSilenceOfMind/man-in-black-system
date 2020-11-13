package com.itmo.mibsystem.dao.hrmanager;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MIBEmployeeRepository extends CrudRepository<MIBEmployee, Long> {
    Optional<User> findByUsername(String username);
}