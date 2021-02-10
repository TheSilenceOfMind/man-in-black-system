package com.itmo.mibsystem.dao.researcher;

import com.itmo.mibsystem.model.researcher.Technology;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TechnologyRepository extends CrudRepository<Technology, Long> {
    Optional<Technology> findByName(String name);

    List<Technology> findByIdSource(Long idSource);

    List<Technology> findByIdRace(Long idRace);

    @Query("SELECT c FROM Technology c WHERE (:name is null or :name = '' or c.name = :name) and (:use is null or :use = '' or c.use = :use) and (:idRace is null or :idRace = 0L or c.idRace = :idRace) and (:isSource is null or :isSource = 0L or c.idSource = :isSource) and (:description is null or :description = '' or c.description = :description) order by c.technologyId ASC")
    List<Technology> findTechnologyByNameAndUseAndIdRaceAAndIdSourceAndDescription(@Param("name") String name, @Param("use") String homePlanet, @Param("idRace") Long idRace, @Param("isSource") Long idSource, @Param("description") String description);
}