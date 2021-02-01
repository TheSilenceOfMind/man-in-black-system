package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.DistributeTechnologyItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface DistributeTechnologyItemRepository extends CrudRepository<DistributeTechnologyItem, Long> {
    Optional<DistributeTechnologyItem> findAllByDescription(String description);

    @Query("SELECT c FROM DistributeTechnologyItem c " +
            "WHERE (:use is null or :use = '' or c.use = :use) and " +
            "(:count is null or :count = 0L or c.count = :count) and " +
            "(:idTechnology is null or :idTechnology = 0L or c.idTechnology = :idTechnology) and " +
            "(:idAgent is null or :idAgent = 0L or c.idAgent = :idAgent) and " +
            "(:description is null or :description = '' or c.description = :description) order by " +
            "c.distributeTechnologyItemId ASC")
    List<DistributeTechnologyItem> findDistributeTechnologyItemsByIdTechnologyAndIdAgentAnd(
            @Param("count") Long count, @Param("use") String use,
            @Param("idTechnology") Long idTechnology,
            @Param("idAgent") Long idAgent,
            @Param("description") String description);
}