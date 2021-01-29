package com.itmo.mibsystem.db;

import static java.util.Collections.emptyList;

import com.itmo.mibsystem.dao.distribute.technology.DeliveryTypeRepository;
import com.itmo.mibsystem.dao.distribute.technology.DistributeTechnologyItemRepository;
import com.itmo.mibsystem.dao.distribute.technology.SellTechnologyDocumentRepository;
import com.itmo.mibsystem.dao.hrmanager.MIBEmployeeRepository;
import com.itmo.mibsystem.dao.hrmanager.UserRepository;
import com.itmo.mibsystem.dao.lawyer.EarthDocumentRepository;
import com.itmo.mibsystem.dao.op_agent.ActDetentionRepository;
import com.itmo.mibsystem.dao.pasportist.AlienPassportRepository;
import com.itmo.mibsystem.dao.pasportist.AlienRaceRepository;
import com.itmo.mibsystem.dao.researcher.SourceTechnologyRepository;
import com.itmo.mibsystem.dao.researcher.TechnologyRepository;
import com.itmo.mibsystem.model.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RepositoryTests {

    private static long userId;

    @BeforeAll
    static void setUp() {
        userId = 10;
    }

    @Autowired
    DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    DistributeTechnologyItemRepository distributeTechnologyItemRepository;

    @Autowired
    SellTechnologyDocumentRepository sellTechnologyDocumentRepository;

    @Autowired
    MIBEmployeeRepository mibEmployeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EarthDocumentRepository earthDocumentRepository;

    @Autowired
    ActDetentionRepository actDetentionRepository;

    @Autowired
    AlienPassportRepository alienPassportRepository;

    @Autowired
    AlienRaceRepository alienRaceRepository;

    @Autowired
    SourceTechnologyRepository sourceTechnologyRepository;

    @Autowired
    TechnologyRepository technologyRepository;

    @Test
    void createAndFindUser() {
        User given = generateUser();

        userRepository.save(given);
        Optional<User> found = userRepository.findByUsername("username" + userId);
        if (found.isPresent()) {
            given.setUserId(found.get().getUserId());
            assertEquals(given, found.get());
        } else {
            throw new RuntimeException("Not found!");
        }
    }

    private static User generateUser() {
        userId++;
        return new User(userId, "username" + userId, "password", false, false, false, false, emptyList());
    }
}
