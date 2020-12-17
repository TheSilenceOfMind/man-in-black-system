package com.itmo.mibsystem.dao.pasportist;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.pasportist.AlienRace;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlienRaceRepository extends CrudRepository<AlienRace, Long> {
    Optional<AlienRace> findByName(String name);
}