package com.itmo.mibsystem.dao.pasportist;

import com.itmo.mibsystem.model.passporter.AlienPassport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlienPassportRepository extends CrudRepository<AlienPassport, Long> {
    Optional<AlienPassport> findByName(String name);

    @Query("SELECT c FROM AlienPassport c WHERE (:name is null or :name = '' or c.name = :name) and (:homePlanet is null or :homePlanet = '' or c.homePlanet = :homePlanet) and (:idRace is null or :idRace = 0L or c.idRace = :idRace) and (:description is null or :description = '' or c.description = :description) order by c.passportId ASC")
    List<AlienPassport> findAlienPassportByNameAndHomePlanetAndIdRaceAndDescription(@Param("name") String name, @Param("homePlanet") String homePlanet, @Param("idRace") Long idRace, @Param("description") String description);

    List<AlienPassport> findAllByIdRace(Long idRace);
}