package com.itmo.mibsystem.dao.op_agent;

import com.itmo.mibsystem.model.op_agent.ActDetention;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActDetentionRepository extends CrudRepository<ActDetention, Long> {
    @Query("SELECT c FROM ActDetention c " +
            "WHERE (:scene is null or :scene = '' or c.scene = :scene) and " +
            "(:idGuiltyAlien is null or :idGuiltyAlien = 0L or c.idGuiltyAlien = :idGuiltyAlien) and " +
            "(:idUserAgent is null or :idUserAgent = 0L or c.idUserAgent = :idUserAgent) and " +
            "(:description is null or :description = '' or c.description = :description) order by " +
            "c.actDetentionId ASC")
    List<ActDetention> findActDetentionBySceneAndIdGuiltyAlienAndIdUserAgentAndDescription(@Param("scene") String scene, @Param("idGuiltyAlien") Long idGuiltyAlien, @Param("idUserAgent") Long idUserAgent, @Param("description") String description);

    List<ActDetention> findByIdUserAgent(Long idUserAgent);

    Optional<ActDetention> findByScene(String scene);
}