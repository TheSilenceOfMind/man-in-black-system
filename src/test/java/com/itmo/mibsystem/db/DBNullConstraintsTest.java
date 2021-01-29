package com.itmo.mibsystem.db;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.itmo.mibsystem.dao.hrmanager.RoleRepository;
import com.itmo.mibsystem.dao.hrmanager.UserRepository;
import com.itmo.mibsystem.dao.pasportist.AlienPassportRepository;
import com.itmo.mibsystem.dao.pasportist.AlienRaceRepository;
import com.itmo.mibsystem.dao.researcher.SourceTechnologyRepository;
import com.itmo.mibsystem.dao.researcher.TechnologyRepository;
import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.model.researcher.SourceTechnology;
import com.itmo.mibsystem.model.researcher.Technology;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class DBNullConstraintsTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlienPassportRepository alienPassportRepository;

    @Autowired
    private AlienRaceRepository alienRaceRepository;

    @Autowired
    private SourceTechnologyRepository sourceTechnologyRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Test
    void saveRoleWithNull_shouldThrowException() {
        Role role = new Role();
        assertThrows(DataIntegrityViolationException.class,
            () -> roleRepository.save(role)
        );
    }

    @Test
    void saveUserWithNull_shouldThrowException() {
        User user = new User();
        assertThrows(DataIntegrityViolationException.class,
            () -> userRepository.save(user)
        );
    }

    @Test
    void saveAlienPassportWithNull_shouldThrowException() {
        AlienPassport alienPassport = new AlienPassport();
        assertThrows(DataIntegrityViolationException.class,
            () -> alienPassportRepository.save(alienPassport)
        );
    }

    @Test
    void saveAlienRaceWithNull_shouldThrowException() {
        AlienRace alienRace = new AlienRace();
        assertThrows(DataIntegrityViolationException.class,
            () -> alienRaceRepository.save(alienRace)
        );
    }

    @Test
    void saveSourceTechnologyWithNull_shouldThrowException() {
        SourceTechnology sourceTechnology = new SourceTechnology();
        assertThrows(DataIntegrityViolationException.class,
            () -> sourceTechnologyRepository.save(sourceTechnology)
        );
    }

    @Test
    void saveTechnologyWithNull_shouldThrowException() {
        Technology technology = new Technology();
        assertThrows(DataIntegrityViolationException.class,
            () -> technologyRepository.save(technology)
        );
    }
}
