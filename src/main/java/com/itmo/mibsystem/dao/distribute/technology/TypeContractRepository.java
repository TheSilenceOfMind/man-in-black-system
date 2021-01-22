package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.TypeContract;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeContractRepository extends CrudRepository<TypeContract, Long> {
    Optional<TypeContract> findAllByType(String type);
}