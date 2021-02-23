package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.pasportist.AlienPassportRepository;
import com.itmo.mibsystem.dao.pasportist.AlienRaceRepository;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.model.researcher.Technology;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PassporterServiceTests {

    @Autowired
    PassporterService passporterService;

    @Autowired
    AlienPassportRepository alienPassportRepository;

    @Autowired
    AlienRaceRepository alienRaceRepository;

    @Test
    void checkInsertPassport() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");

        //Test
        AlienPassport pass = new AlienPassport(null, "testName", "testPlanet", "testDescription", race.getRaceId(), false);
        pass = passporterService.insertPassport(pass);

        AlienPassport findPass = alienPassportRepository.findById(pass.getPassportId()).orElse(null);

        assertTrue(pass.equals(findPass));

        //Delete test data
        alienPassportRepository.deleteById(pass.getPassportId());
        alienRaceRepository.deleteById(race.getRaceId());
    }

    @Test
    void checkDeletePassport() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");
        AlienPassport pass = CreateTestAlien("testName", "testPlanet", "testDescription", race.getRaceId());

        //Test
        passporterService.deletePassport(pass);

        pass = alienPassportRepository.findByName("testName").orElse(null);
        assertNull(pass);

        //Delete test data
        alienRaceRepository.deleteById(race.getRaceId());
    }

    @Test
    void checkUpdatePassport() {
        //Create test data
        AlienRace race1 = CreateTestRace("testRace1");
        AlienRace race2 = CreateTestRace("testRace2");
        AlienPassport pass = CreateTestAlien("testName", "testPlanet", "testDescription", race1.getRaceId());

        //Test
        pass.setName("testName2");
        pass.setHomePlanet("testPlanet2");
        pass.setDescription("testDescription2");
        pass.setIdRace(race2.getRaceId());
        AlienPassport updatePass = passporterService.updatePassport(pass);

        assertTrue(updatePass.equals(pass));

        //Delete test data
        alienPassportRepository.deleteById(pass.getPassportId());
        alienRaceRepository.deleteById(race1.getRaceId());
        alienRaceRepository.deleteById(race2.getRaceId());
    }

    @Test
    void checkGetAliensByFildsAll() {
        //Create test data
        AlienRace race1 = CreateTestRace("testRace1");
        AlienRace race2 = CreateTestRace("testRace2");
        List<AlienPassport> newAliens = new ArrayList<AlienPassport>();
        newAliens.add(CreateTestAlien("testName1", "testPlanet1", "testDescription1", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName2", "testPlanet1", "testDescription1", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName3", "testPlanet2", "testDescription1", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName4", "testPlanet1", "testDescription2", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName5", "testPlanet1", "testDescription1", race2.getRaceId()));

        //Test
        List<AlienPassport> aliens = passporterService.getAliensByFilds("", "", 0L, "");
        int countFind = 0;
        for(int i = 0; i < aliens.size(); i++) {
            for(int j = 0; j < newAliens.size(); j++) {
                if(aliens.get(i).getPassportId() == newAliens.get(j).getPassportId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 5);

        //Delete test data
        for (AlienPassport alien: newAliens) {
            alienPassportRepository.deleteById(alien.getPassportId());
        }
        alienRaceRepository.deleteById(race1.getRaceId());
        alienRaceRepository.deleteById(race2.getRaceId());
    }

    @Test
    void checkGetAliensByFildsOne() {
        //Create test data
        AlienRace race1 = CreateTestRace("testRace1");
        AlienRace race2 = CreateTestRace("testRace2");
        List<AlienPassport> newAliens = new ArrayList<AlienPassport>();
        newAliens.add(CreateTestAlien("testName1", "testPlanet1", "testDescription1", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName2", "testPlanet1", "testDescription1", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName3", "testPlanet2", "testDescription1", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName4", "testPlanet1", "testDescription2", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName5", "testPlanet1", "testDescription1", race2.getRaceId()));

        //Test
        List<AlienPassport> aliens = passporterService.getAliensByFilds("testName2", "", 0L, "");
        int countFind = 0;
        for(int i = 0; i < aliens.size(); i++) {
            for(int j = 0; j < newAliens.size(); j++) {
                if(aliens.get(i).getPassportId() == newAliens.get(j).getPassportId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);

        aliens = passporterService.getAliensByFilds("", "", race2.getRaceId(), "");
        countFind = 0;
        for(int i = 0; i < aliens.size(); i++) {
            for(int j = 0; j < newAliens.size(); j++) {
                if(aliens.get(i).getPassportId() == newAliens.get(j).getPassportId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);

        //Delete test data
        for (AlienPassport alien: newAliens) {
            alienPassportRepository.deleteById(alien.getPassportId());
        }
        alienRaceRepository.deleteById(race1.getRaceId());
        alienRaceRepository.deleteById(race2.getRaceId());
    }

    @Test
    void checkGetAliensByFildsAny() {
        //Create test data
        AlienRace race1 = CreateTestRace("testRace1");
        AlienRace race2 = CreateTestRace("testRace2");
        List<AlienPassport> newAliens = new ArrayList<AlienPassport>();
        newAliens.add(CreateTestAlien("testName1", "testPlanet1", "testDescription1", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName2", "testPlanet1", "testDescription1", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName3", "testPlanet2", "testDescription1", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName4", "testPlanet1", "testDescription2", race1.getRaceId()));
        newAliens.add(CreateTestAlien("testName5", "testPlanet1", "testDescription1", race2.getRaceId()));

        //Test
        List<AlienPassport> aliens = passporterService.getAliensByFilds("", "", race1.getRaceId(), "");
        int countFind = 0;
        for(int i = 0; i < aliens.size(); i++) {
            for(int j = 0; j < newAliens.size(); j++) {
                if(aliens.get(i).getPassportId() == newAliens.get(j).getPassportId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 4);

        //Delete test data
        for (AlienPassport alien: newAliens) {
            alienPassportRepository.deleteById(alien.getPassportId());
        }
        alienRaceRepository.deleteById(race1.getRaceId());
        alienRaceRepository.deleteById(race2.getRaceId());
    }

    @Test
    void checkGetAllAliens() {
        int countAliens = 0;
        List<AlienPassport> aliensService = passporterService.getAliens();

        List<AlienPassport> aliensRepository = new ArrayList<AlienPassport>();
        alienPassportRepository.findAll().iterator().forEachRemaining(aliensRepository::add);


        for(int i = 0; i < aliensRepository.size(); i++) {
            for(int j = 0; j < aliensService.size(); j++) {
                if (aliensRepository.get(i).getPassportId() == aliensService.get(j).getPassportId()) {
                    countAliens++;
                }
            }
        }

        assertTrue(countAliens == aliensRepository.size());
    }

    AlienRace CreateTestRace(String Name) {
        AlienRace race = alienRaceRepository.findByName(Name).orElse(null);

        if(race != null) {
            List<AlienPassport> buff = alienPassportRepository.findAllByIdRace(race.getRaceId());
            for(int i = 0; i < buff.size(); i++) {
                alienPassportRepository.deleteById(buff.get(i).getPassportId());
            }
            alienRaceRepository.deleteById(race.getRaceId());
        }
        return alienRaceRepository.save(new AlienRace(Name));
    }

    AlienPassport CreateTestAlien(String name, String homePlane, String description, Long idRace) {
        AlienPassport pass = alienPassportRepository.findByName(name).orElse(null);
        if(pass != null) {
            alienPassportRepository.deleteById(pass.getPassportId());
        }

        return alienPassportRepository.save(new AlienPassport(null, name, homePlane, description, idRace, false));
    }

}
