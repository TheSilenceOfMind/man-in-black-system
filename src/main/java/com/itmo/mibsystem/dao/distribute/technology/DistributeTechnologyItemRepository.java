package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.DistributeTechnologyItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface DistributeTechnologyItemRepository extends CrudRepository<DistributeTechnologyItem, Long> {
    Optional<DistributeTechnologyItem> findAllByDescription(String description);

    @Query("SELECT c FROM DistributeTechnologyItem c")
    List<DistributeTechnologyItem> findDistributeTechnologyItemsByIdTechnologyAndIdAgentAnd(Long count, long idTechnology, long idAgent, String description);
}