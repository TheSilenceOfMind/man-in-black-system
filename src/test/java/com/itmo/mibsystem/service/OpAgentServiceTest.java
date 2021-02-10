package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.hrmanager.MIBEmployeeRepository;
import com.itmo.mibsystem.dao.hrmanager.UserRepository;
import com.itmo.mibsystem.dao.op_agent.ActDetentionRepository;
import com.itmo.mibsystem.dao.pasportist.AlienPassportRepository;
import com.itmo.mibsystem.dao.pasportist.AlienRaceRepository;
import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import com.itmo.mibsystem.model.op_agent.ActDetention;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.model.researcher.Technology;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OpAgentServiceTest {
    @Autowired
    OpAgentService opAgentService;

    @Autowired
    ActDetentionRepository actDetentionRepository;

    @Autowired
    MIBEmployeeRepository mIBEmployeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AlienPassportRepository alienPassportRepository;

    @Autowired
    AlienRaceRepository alienRaceRepository;

    @Test
    void checkInsertActDetention(){
        //Create test data
        AlienRace testRace = CreateTestRace("testRace");
        AlienPassport alien = CreateTestAlien("testName", "testHomePlanet","testDescription", testRace.getRaceId());

        User testUser = CreateTestUser("testUsername","testPassword");
        MIBEmployee mibEmployee = CreateMIBEmployee("testName", "11 age", "testDiscription", testUser.getUserId());

        //Test
        ActDetention actDetention = new ActDetention("testScene", "testDescription", alien.getPassportId(), mibEmployee.getMIBEmployeeId());
        actDetention = opAgentService.insertActDetention(actDetention);

        ActDetention findMibEmployee = actDetentionRepository.findById(actDetention.getActDetentionId()).orElse(null);

        assertTrue(actDetention.equals(findMibEmployee));

        //Delete test data
        actDetentionRepository.deleteById(findMibEmployee.getActDetentionId());

        alienPassportRepository.deleteById(alien.getPassportId());
        alienRaceRepository.deleteById(testRace.getRaceId());

        mIBEmployeeRepository.deleteById(mibEmployee.getMIBEmployeeId());
        userRepository.deleteById(testUser.getUserId());

    }

    @Test
    void checkDeleteActDetention(){
        //Create test data
        AlienRace testRace = CreateTestRace("testRace");
        AlienPassport alien = CreateTestAlien("testName", "testHomePlanet","testDescription", testRace.getRaceId());

        User testUser = CreateTestUser("testUsername","testPassword");
        MIBEmployee mibEmployee = CreateMIBEmployee("testName", "11 age", "testDiscription", testUser.getUserId());

        //Test
        ActDetention actDetention = CreateTestActDetention("testScene", "testDescription", alien.getPassportId(), mibEmployee.getMIBEmployeeId());
        opAgentService.deleteActDetention(actDetention);

        actDetention = actDetentionRepository.findById(actDetention.getActDetentionId()).orElse(null);
        assertNull(actDetention);

        //Delete test data
        alienPassportRepository.deleteById(alien.getPassportId());
        alienRaceRepository.deleteById(testRace.getRaceId());

        mIBEmployeeRepository.deleteById(mibEmployee.getMIBEmployeeId());
        userRepository.deleteById(testUser.getUserId());
    }

    @Test
    void checkUpdateActDetention(){
        //Create test data
        AlienRace testRace = CreateTestRace("testRace");
        AlienPassport alien = CreateTestAlien("testName", "testHomePlanet","testDescription", testRace.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2", "testHomePlanet2","testDescription2", testRace.getRaceId());

        User testUser = CreateTestUser("testUsername","testPassword");
        MIBEmployee mibEmployee = CreateMIBEmployee("testName", "11 age", "testDiscription", testUser.getUserId());
        MIBEmployee mibEmployee2 = CreateMIBEmployee("testName2", "12 age", "testDiscription2", testUser.getUserId());

        //Test
        ActDetention actDetention = CreateTestActDetention("testScene1", "testDescription1", alien.getPassportId(), mibEmployee.getMIBEmployeeId());
        actDetention.setScene("testScene2");
        actDetention.setDescription("testDescription2");
        actDetention.setIdGuiltyAlien(alien2.getPassportId());
        actDetention.setIdUserAgent(mibEmployee2.getMIBEmployeeId());
        ActDetention updateAct = opAgentService.updateActDetention(actDetention);

        assertTrue(actDetention.equals(updateAct));

        //Delete test data
        actDetentionRepository.deleteById(updateAct.getActDetentionId());
        alienPassportRepository.deleteById(alien.getPassportId());
        alienPassportRepository.deleteById(alien2.getPassportId());
        alienRaceRepository.deleteById(testRace.getRaceId());

        mIBEmployeeRepository.deleteById(mibEmployee.getMIBEmployeeId());
        mIBEmployeeRepository.deleteById(mibEmployee2.getMIBEmployeeId());
        userRepository.deleteById(testUser.getUserId());
    }

    @Test
    void checkGetActDetentionByFildsAll(){
        //Create test data
        AlienRace testRace = CreateTestRace("testRace");
        AlienPassport alien = CreateTestAlien("testName", "testHomePlanet","testDescription", testRace.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2", "testHomePlanet2","testDescription2", testRace.getRaceId());

        User testUser = CreateTestUser("testUsername","testPassword");
        MIBEmployee mibEmployee = CreateMIBEmployee("testName", "11 age", "testDiscription", testUser.getUserId());
        MIBEmployee mibEmployee2 = CreateMIBEmployee("testName2", "12 age", "testDiscription2", testUser.getUserId());

        List<ActDetention> actDetentions = new ArrayList<ActDetention>();
        actDetentions.add(CreateTestActDetention("testScene1", "testDescription1", alien.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene2", "testDescription1", alien.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene3", "testDescription2", alien.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene4", "testDescription1", alien2.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene5", "testDescription1", alien.getPassportId(), mibEmployee2.getMIBEmployeeId()));

        //Test
        List<ActDetention> findActDetentions = opAgentService.getActDetentionByFilds("", 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < findActDetentions.size(); i++) {
            for(int j = 0; j < actDetentions.size(); j++) {
                if(findActDetentions.get(i).getActDetentionId() == actDetentions.get(j).getActDetentionId()) {
                    countFind++;
                }
            }
        }
        assertTrue(countFind == 5);

        //Delete test data
        for (ActDetention actDetention: actDetentions) {
            actDetentionRepository.deleteById(actDetention.getActDetentionId());
        }
        alienPassportRepository.deleteById(alien.getPassportId());
        alienPassportRepository.deleteById(alien2.getPassportId());
        alienRaceRepository.deleteById(testRace.getRaceId());

        mIBEmployeeRepository.deleteById(mibEmployee.getMIBEmployeeId());
        mIBEmployeeRepository.deleteById(mibEmployee2.getMIBEmployeeId());
        userRepository.deleteById(testUser.getUserId());
    }

    @Test
    void checkGetActDetentionByFildsOne(){
        //Create test data
        AlienRace testRace = CreateTestRace("testRace");
        AlienPassport alien = CreateTestAlien("testName", "testHomePlanet","testDescription", testRace.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2", "testHomePlanet2","testDescription2", testRace.getRaceId());

        User testUser = CreateTestUser("testUsername","testPassword");
        MIBEmployee mibEmployee = CreateMIBEmployee("testName", "11 age", "testDiscription", testUser.getUserId());
        MIBEmployee mibEmployee2 = CreateMIBEmployee("testName2", "12 age", "testDiscription2", testUser.getUserId());

        List<ActDetention> actDetentions = new ArrayList<ActDetention>();
        actDetentions.add(CreateTestActDetention("testScene1", "testDescription1", alien.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene2", "testDescription1", alien.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene3", "testDescription2", alien.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene4", "testDescription1", alien2.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene5", "testDescription1", alien.getPassportId(), mibEmployee2.getMIBEmployeeId()));

        //Test
        List<ActDetention> findActDetentions = opAgentService.getActDetentionByFilds("", alien2.getPassportId(), mibEmployee.getMIBEmployeeId(), "");
        int countFind = 0;
        for(int i = 0; i < findActDetentions.size(); i++) {
            for(int j = 0; j < actDetentions.size(); j++) {
                if(findActDetentions.get(i).getActDetentionId() == actDetentions.get(j).getActDetentionId()) {
                    countFind++;
                }
            }
        }
        assertTrue(countFind == 1);

        //Delete test data
        for (ActDetention actDetention: actDetentions) {
            actDetentionRepository.deleteById(actDetention.getActDetentionId());
        }
        alienPassportRepository.deleteById(alien.getPassportId());
        alienPassportRepository.deleteById(alien2.getPassportId());
        alienRaceRepository.deleteById(testRace.getRaceId());

        mIBEmployeeRepository.deleteById(mibEmployee.getMIBEmployeeId());
        mIBEmployeeRepository.deleteById(mibEmployee2.getMIBEmployeeId());
        userRepository.deleteById(testUser.getUserId());
    }

    @Test
    void checkGetActDetentionByFildsAny(){
        //Create test data
        AlienRace testRace = CreateTestRace("testRace");
        AlienPassport alien = CreateTestAlien("testName", "testHomePlanet","testDescription", testRace.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2", "testHomePlanet2","testDescription2", testRace.getRaceId());

        User testUser = CreateTestUser("testUsername","testPassword");
        MIBEmployee mibEmployee = CreateMIBEmployee("testName", "11 age", "testDiscription", testUser.getUserId());
        MIBEmployee mibEmployee2 = CreateMIBEmployee("testName2", "12 age", "testDiscription2", testUser.getUserId());

        List<ActDetention> actDetentions = new ArrayList<ActDetention>();
        actDetentions.add(CreateTestActDetention("testScene1", "testDescription1", alien.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene2", "testDescription1", alien.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene3", "testDescription2", alien.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene4", "testDescription1", alien2.getPassportId(), mibEmployee.getMIBEmployeeId()));
        actDetentions.add(CreateTestActDetention("testScene5", "testDescription1", alien.getPassportId(), mibEmployee2.getMIBEmployeeId()));

        //Test
        List<ActDetention> findActDetentions = opAgentService.getActDetentionByFilds("",0L, mibEmployee.getMIBEmployeeId(), "");
        int countFind = 0;
        for(int i = 0; i < findActDetentions.size(); i++) {
            for(int j = 0; j < actDetentions.size(); j++) {
                if(findActDetentions.get(i).getActDetentionId() == actDetentions.get(j).getActDetentionId()) {
                    countFind++;
                }
            }
        }
        assertTrue(countFind == 4);

        //Delete test data
        for (ActDetention actDetention: actDetentions) {
            actDetentionRepository.deleteById(actDetention.getActDetentionId());
        }
        alienPassportRepository.deleteById(alien.getPassportId());
        alienPassportRepository.deleteById(alien2.getPassportId());
        alienRaceRepository.deleteById(testRace.getRaceId());

        mIBEmployeeRepository.deleteById(mibEmployee.getMIBEmployeeId());
        mIBEmployeeRepository.deleteById(mibEmployee2.getMIBEmployeeId());
        userRepository.deleteById(testUser.getUserId());
    }

    @Test
    void checkGetAllActDetention(){
        int countActDetentions = 0;
        List<ActDetention> actDetentionsService = opAgentService.getActDetention();

        List<ActDetention> actDetentionsRepository = new ArrayList<ActDetention>();
        actDetentionRepository.findAll().iterator().forEachRemaining(actDetentionsRepository::add);


        for(int i = 0; i < actDetentionsRepository.size(); i++) {
            for(int j = 0; j < actDetentionsService.size(); j++) {
                if (actDetentionsRepository.get(i).getActDetentionId() == actDetentionsService.get(j).getActDetentionId()) {
                    countActDetentions++;
                }
            }
        }

        assertTrue(countActDetentions == actDetentionsRepository.size());
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

        return alienPassportRepository.save(new AlienPassport(null, name, homePlane, description, idRace));
    }

    User CreateTestUser(String username, String pasword) {
        User user = userRepository.findByUsername(username).orElse(null);

        if(user != null) {
            List<MIBEmployee> buff = mIBEmployeeRepository.findByIdUser(user.getUserId());
            for(int i = 0; i < buff.size(); i++) {
                mIBEmployeeRepository.deleteById(buff.get(i).getMIBEmployeeId());
            }
            userRepository.deleteById(user.getUserId());
        }
        return userRepository.save(new User(0L, username, pasword, false));
    }

    MIBEmployee CreateMIBEmployee(String name, String age, String description, Long idUser) {
        MIBEmployee mIBEmployee = mIBEmployeeRepository.findAllByName(name).orElse(null);
        if(mIBEmployee != null) {
            mIBEmployeeRepository.deleteById(mIBEmployee.getMIBEmployeeId());
        }

        return mIBEmployeeRepository.save(new MIBEmployee(name, age, description, idUser, null));
    }

    ActDetention CreateTestActDetention(String scene, String description, Long idGuiltyAlien, Long idUserAgent) {
        ActDetention act = actDetentionRepository.findByScene(scene).orElse(null);
        if(act != null) {
            actDetentionRepository.deleteById(act.getActDetentionId());
        }

        return actDetentionRepository.save(new ActDetention(scene, description, idGuiltyAlien, idUserAgent));
    }

}
