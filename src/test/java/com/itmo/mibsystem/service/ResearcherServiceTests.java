package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.pasportist.AlienPassportRepository;
import com.itmo.mibsystem.dao.pasportist.AlienRaceRepository;
import com.itmo.mibsystem.dao.researcher.SourceTechnologyRepository;
import com.itmo.mibsystem.dao.researcher.TechnologyRepository;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.model.researcher.SourceTechnology;
import com.itmo.mibsystem.model.researcher.Technology;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ResearcherServiceTests {
    @Autowired
    ResearcherService researcherService;

    @Autowired
    SourceTechnologyRepository sourceTechnologyRepository;

    @Autowired
    TechnologyRepository technologyRepository;

    @Autowired
    AlienPassportRepository alienPassportRepository;

    @Autowired
    AlienRaceRepository alienRaceRepository;

    @Test
    void checkInsertTechnology() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");
        SourceTechnology source = CreateTestSourceTechnology("testSourceTechnology");

        //Test
        Technology tech = new Technology("testName", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        tech = researcherService.insertTechnology(tech);

        Technology findTech = technologyRepository.findById(tech.getTechnologyId()).orElse(null);

        assertTrue(tech.equals(findTech));

        //Delete test data
        technologyRepository.deleteById(tech.getTechnologyId());
        alienRaceRepository.deleteById(race.getRaceId());
        sourceTechnologyRepository.deleteById(source.getSourceId());
    }

    @Test
    void checkDeleteTechnology() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");
        SourceTechnology source = CreateTestSourceTechnology("testSourceTechnology");

        //Test
        Technology tech = CreateTestTechnology("testName", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        researcherService.deleteTechnology(tech);

        tech = technologyRepository.findById(tech.getTechnologyId()).orElse(null);
        assertNull(tech);

        //Delete test data
        alienRaceRepository.deleteById(race.getRaceId());
        sourceTechnologyRepository.deleteById(source.getSourceId());
    }

    @Test
    void checkUpdateTechnology() {
        //Create test data
        AlienRace race1 = CreateTestRace("testRace1");
        AlienRace race2 = CreateTestRace("testRace2");
        SourceTechnology source1 = CreateTestSourceTechnology("testSourceTechnology1");
        SourceTechnology source2 = CreateTestSourceTechnology("testSourceTechnology2");
        Technology tech = CreateTestTechnology("testName", "testUse", "testDescription", race1.getRaceId(), source1.getSourceId());

        //Test
        tech.setName("testName2");
        tech.setUse("testUse2");
        tech.setDescription("testDescription2");
        tech.setIdRace(race2.getRaceId());
        tech.setIdSource(source2.getSourceId());
        Technology updateTech = researcherService.updateTechnology(tech);

        assertTrue(tech.equals(updateTech));

        //Delete test data
        technologyRepository.deleteById(tech.getTechnologyId());
        alienRaceRepository.deleteById(race1.getRaceId());
        alienRaceRepository.deleteById(race2.getRaceId());
        sourceTechnologyRepository.deleteById(source1.getSourceId());
        sourceTechnologyRepository.deleteById(source2.getSourceId());
    }

    @Test
    void checkGetTechnologysByFildsAll() {
        //Create test data
        AlienRace race1 = CreateTestRace("testRace1");
        AlienRace race2 = CreateTestRace("testRace2");
        SourceTechnology source1 = CreateTestSourceTechnology("testSourceTechnology1");
        SourceTechnology source2 = CreateTestSourceTechnology("testSourceTechnology2");
        List<Technology> newTech = new ArrayList<Technology>();
        newTech.add(CreateTestTechnology("testName1", "testUse1", "testDescription1", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName2", "testUse1", "testDescription1", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName3", "testUse2", "testDescription1", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName4", "testUse1", "testDescription2", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName5", "testUse1", "testDescription1", race2.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName6", "testUse1", "testDescription1", race1.getRaceId(), source2.getSourceId()));

        //Test
        List<Technology> techs = researcherService.getTechnologysByFilds("", "", 0L, 0L,"");
        int countFind = 0;
        for(int i = 0; i < techs.size(); i++) {
            for(int j = 0; j < newTech.size(); j++) {
                if(techs.get(i).getTechnologyId() == newTech.get(j).getTechnologyId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 6);

        //Delete test data
        for (Technology tech: newTech) {
            technologyRepository.deleteById(tech.getTechnologyId());
        }
        alienRaceRepository.deleteById(race1.getRaceId());
        alienRaceRepository.deleteById(race2.getRaceId());
        sourceTechnologyRepository.deleteById(source1.getSourceId());
        sourceTechnologyRepository.deleteById(source2.getSourceId());

    }

    @Test
    void checkGetTechnologysByFildsOne() {
        //Create test data
        AlienRace race1 = CreateTestRace("testRace1");
        AlienRace race2 = CreateTestRace("testRace2");
        SourceTechnology source1 = CreateTestSourceTechnology("testSourceTechnology1");
        SourceTechnology source2 = CreateTestSourceTechnology("testSourceTechnology2");
        List<Technology> newTech = new ArrayList<Technology>();
        newTech.add(CreateTestTechnology("testName1", "testUse1", "testDescription1", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName2", "testUse1", "testDescription1", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName3", "testUse2", "testDescription1", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName4", "testUse1", "testDescription2", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName5", "testUse1", "testDescription1", race2.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName6", "testUse1", "testDescription1", race1.getRaceId(), source2.getSourceId()));

        //Test
        List<Technology> techs = researcherService.getTechnologysByFilds("", "", race2.getRaceId(), source1.getSourceId(),"");
        int countFind = 0;
        for(int i = 0; i < techs.size(); i++) {
            for(int j = 0; j < newTech.size(); j++) {
                if(techs.get(i).getTechnologyId() == newTech.get(j).getTechnologyId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);

        //Delete test data
        for (Technology tech: newTech) {
            technologyRepository.deleteById(tech.getTechnologyId());
        }
        alienRaceRepository.deleteById(race1.getRaceId());
        alienRaceRepository.deleteById(race2.getRaceId());
        sourceTechnologyRepository.deleteById(source1.getSourceId());
        sourceTechnologyRepository.deleteById(source2.getSourceId());
    }

    @Test
    void checkGetTechnologysByFildsAny() {
        //Create test data
        AlienRace race1 = CreateTestRace("testRace1");
        AlienRace race2 = CreateTestRace("testRace2");
        SourceTechnology source1 = CreateTestSourceTechnology("testSourceTechnology1");
        SourceTechnology source2 = CreateTestSourceTechnology("testSourceTechnology2");
        List<Technology> newTech = new ArrayList<Technology>();
        newTech.add(CreateTestTechnology("testName1", "testUse1", "testDescription1", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName2", "testUse1", "testDescription1", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName3", "testUse2", "testDescription1", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName4", "testUse1", "testDescription2", race1.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName5", "testUse1", "testDescription1", race2.getRaceId(), source1.getSourceId()));
        newTech.add(CreateTestTechnology("testName6", "testUse1", "testDescription1", race1.getRaceId(), source2.getSourceId()));

        //Test
        List<Technology> techs = researcherService.getTechnologysByFilds("", "", race1.getRaceId(), 0L,"");
        int countFind = 0;
        for(int i = 0; i < techs.size(); i++) {
            for(int j = 0; j < newTech.size(); j++) {
                if(techs.get(i).getTechnologyId() == newTech.get(j).getTechnologyId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 5);

        //Delete test data
        for (Technology tech: newTech) {
            technologyRepository.deleteById(tech.getTechnologyId());
        }
        alienRaceRepository.deleteById(race1.getRaceId());
        alienRaceRepository.deleteById(race2.getRaceId());
        sourceTechnologyRepository.deleteById(source1.getSourceId());
        sourceTechnologyRepository.deleteById(source2.getSourceId());
    }

    @Test
    void checkGetAllTechnologys() {
        //Create test data
        int countTech = 0;
        List<Technology> techService = researcherService.getTechnologies();

        //Test
        List<Technology> techRepository = new ArrayList<Technology>();
        technologyRepository.findAll().iterator().forEachRemaining(techRepository::add);


        for(int i = 0; i < techRepository.size(); i++) {
            for(int j = 0; j < techService.size(); j++) {
                if (techRepository.get(i).getTechnologyId() == techService.get(j).getTechnologyId()) {
                    countTech++;
                }
            }
        }

        assertTrue(countTech == techRepository.size());

    }

    SourceTechnology CreateTestSourceTechnology(String value) {
        SourceTechnology sourceTechnology = sourceTechnologyRepository.findByValue(value).orElse(null);

        if(sourceTechnology != null) {
            List<Technology> buff = technologyRepository.findByIdSource(sourceTechnology.getSourceId());
            for(int i = 0; i < buff.size(); i++) {
                technologyRepository.deleteById(buff.get(i).getTechnologyId());
            }
            sourceTechnologyRepository.deleteById(sourceTechnology.getSourceId());
        }
        return sourceTechnologyRepository.save(new SourceTechnology(value));
    }

    AlienRace CreateTestRace(String Name) {
        AlienRace race = alienRaceRepository.findByName(Name).orElse(null);

        if(race != null) {
            List<AlienPassport> buff = alienPassportRepository.findAllByIdRace(race.getRaceId());
            for(int i = 0; i < buff.size(); i++) {
                alienPassportRepository.deleteById(buff.get(i).getPassportId());
            }

            List<Technology> buff2 = technologyRepository.findByIdRace(race.getRaceId());
            for(int i = 0; i < buff2.size(); i++) {
                technologyRepository.deleteById(buff2.get(i).getTechnologyId());
            }

            alienRaceRepository.deleteById(race.getRaceId());
        }
        return alienRaceRepository.save(new AlienRace(Name));
    }

    Technology CreateTestTechnology(String name, String use, String description, Long idRace, Long idSource) {
        Technology pass = technologyRepository.findByName(name).orElse(null);
        if(pass != null) {
            technologyRepository.deleteById(pass.getTechnologyId());
        }

        return technologyRepository.save(new Technology(name, use, description, idRace, idSource));
    }
}
