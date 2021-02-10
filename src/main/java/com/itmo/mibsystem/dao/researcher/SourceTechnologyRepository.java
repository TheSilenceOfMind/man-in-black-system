package com.itmo.mibsystem.dao.researcher;

import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.model.researcher.SourceTechnology;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SourceTechnologyRepository extends CrudRepository<SourceTechnology, Long> {
    Optional<SourceTechnology> findByValue(String value);
}
