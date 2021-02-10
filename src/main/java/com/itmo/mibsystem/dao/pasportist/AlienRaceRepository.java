package com.itmo.mibsystem.dao.pasportist;

import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.model.researcher.Technology;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlienRaceRepository extends CrudRepository<AlienRace, Long> {
    Optional<AlienRace> findByName(String name);

}