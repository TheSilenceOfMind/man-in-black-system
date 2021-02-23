package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.lawyer.EarthDocumentRepository;
import com.itmo.mibsystem.dao.lawyer.NationRepository;
import com.itmo.mibsystem.dao.lawyer.TypeEarthDocumentRepository;
import com.itmo.mibsystem.dao.pasportist.AlienPassportRepository;
import com.itmo.mibsystem.dao.pasportist.AlienRaceRepository;
import com.itmo.mibsystem.model.lawyer.EarthDocument;
import com.itmo.mibsystem.model.lawyer.Nation;
import com.itmo.mibsystem.model.lawyer.TypeEarthDocument;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LawyerServiceTests {
    @Autowired
    LawyerService lawyerService;

    @Autowired
    EarthDocumentRepository earthDocumentRepository;

    @Autowired
    NationRepository nationRepository;

    @Autowired
    TypeEarthDocumentRepository typeEarthDocumentRepository;

    @Autowired
    AlienPassportRepository alienPassportRepository;

    @Autowired
    AlienRaceRepository alienRaceRepository;

    @Test
    void checkInsertEarthDocument() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");
        AlienPassport alien = CreateTestAlien("testName", "testPlanet", "testDescription", race.getRaceId());

        TypeEarthDocument type = CreateTestTypeEarthDocument("typeEarthDocument1");

        Nation nation = CreateTestNation("Nation");

        //Test
        EarthDocument earthDocument = new EarthDocument("testEarthName", nation.getNationId(), type.getTypeEarthDocumentId(), alien.getPassportId(), "testDescription");
        earthDocument = lawyerService.insertEarthDocument(earthDocument);

        EarthDocument findEarthDocument = earthDocumentRepository.findById(earthDocument.getEarthDocumentId()).orElse(null);

        assertTrue(earthDocument.equals(findEarthDocument));

        //Delete test data
        earthDocumentRepository.deleteById(earthDocument.getEarthDocumentId());

        alienPassportRepository.deleteById(alien.getPassportId());
        alienRaceRepository.deleteById(race.getRaceId());

        typeEarthDocumentRepository.deleteById(type.getTypeEarthDocumentId());

        nationRepository.deleteById(nation.getNationId());
    }

    @Test
    void checkDeleteEarthDocument() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");
        AlienPassport alien = CreateTestAlien("testName", "testPlanet", "testDescription", race.getRaceId());

        TypeEarthDocument type = CreateTestTypeEarthDocument("typeEarthDocument1");

        Nation nation = CreateTestNation("Nation");

        //Test
        EarthDocument earthDocument = CreateTestEarthDocument("testEarthName","testDescription", nation.getNationId(), type.getTypeEarthDocumentId(), alien.getPassportId());
        lawyerService.deleteEarthDocument(earthDocument);

        earthDocument = earthDocumentRepository.findById(earthDocument.getEarthDocumentId()).orElse(null);

        assertNull(earthDocument);

        //Delete test data
        alienPassportRepository.deleteById(alien.getPassportId());
        alienRaceRepository.deleteById(race.getRaceId());

        typeEarthDocumentRepository.deleteById(type.getTypeEarthDocumentId());

        nationRepository.deleteById(nation.getNationId());
    }

    @Test
    void checkUpdateEarthDocument() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");
        AlienPassport alien1 = CreateTestAlien("testName", "testPlanet", "testDescription", race.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2", "testPlanet2", "testDescription2", race.getRaceId());

        TypeEarthDocument type1 = CreateTestTypeEarthDocument("typeEarthDocument1");
        TypeEarthDocument type2 = CreateTestTypeEarthDocument("typeEarthDocument2");

        Nation nation1 = CreateTestNation("Nation1");
        Nation nation2 = CreateTestNation("Nation2");

        //Test
        EarthDocument earthDocument = CreateTestEarthDocument("testEarthName","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId());
        earthDocument.setEarthName("testEarthName2");
        earthDocument.setDescription("testDescription2");
        earthDocument.setIdNation(nation2.getNationId());
        earthDocument.setIdTypeDocument(type2.getTypeEarthDocumentId());
        earthDocument.setIdAlien(alien2.getPassportId());
        EarthDocument updateEarthDocument = lawyerService.updateEarthDocument(earthDocument);

        assertTrue(updateEarthDocument.equals(earthDocument));

        //Delete test data
        earthDocumentRepository.deleteById(earthDocument.getEarthDocumentId());

        alienPassportRepository.deleteById(alien1.getPassportId());
        alienPassportRepository.deleteById(alien2.getPassportId());
        alienRaceRepository.deleteById(race.getRaceId());

        typeEarthDocumentRepository.deleteById(type1.getTypeEarthDocumentId());
        typeEarthDocumentRepository.deleteById(type2.getTypeEarthDocumentId());

        nationRepository.deleteById(nation1.getNationId());
        nationRepository.deleteById(nation2.getNationId());

    }

    @Test
    void checkGetEarthDocumentByFildsAll() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");
        AlienPassport alien1 = CreateTestAlien("testName", "testPlanet", "testDescription", race.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2", "testPlanet2", "testDescription2", race.getRaceId());

        TypeEarthDocument type1 = CreateTestTypeEarthDocument("typeEarthDocument1");
        TypeEarthDocument type2 = CreateTestTypeEarthDocument("typeEarthDocument2");

        Nation nation1 = CreateTestNation("Nation1");
        Nation nation2 = CreateTestNation("Nation2");

        List<EarthDocument> newEarthDocument = new ArrayList<EarthDocument>();
        newEarthDocument.add(CreateTestEarthDocument("testEarthName","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName2","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName3","testDescription2", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName4","testDescription", nation2.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName5","testDescription", nation1.getNationId(), type2.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName6","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien2.getPassportId()));

        //Test
        List<EarthDocument> earthDocuments = lawyerService.getEarthDocumentByFilds("", 0L, 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < earthDocuments.size(); i++) {
            for(int j = 0; j < newEarthDocument.size(); j++) {
                if(earthDocuments.get(i).getEarthDocumentId() == newEarthDocument.get(j).getEarthDocumentId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 6);

        //Delete test data
        for (EarthDocument earthDocument: newEarthDocument) {
            earthDocumentRepository.deleteById(earthDocument.getEarthDocumentId());
        }

        alienPassportRepository.deleteById(alien1.getPassportId());
        alienPassportRepository.deleteById(alien2.getPassportId());
        alienRaceRepository.deleteById(race.getRaceId());

        typeEarthDocumentRepository.deleteById(type1.getTypeEarthDocumentId());
        typeEarthDocumentRepository.deleteById(type2.getTypeEarthDocumentId());

        nationRepository.deleteById(nation1.getNationId());
        nationRepository.deleteById(nation2.getNationId());
    }

    @Test
    void checkGetEarthDocumentByFildsOne() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");
        AlienPassport alien1 = CreateTestAlien("testName", "testPlanet", "testDescription", race.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2", "testPlanet2", "testDescription2", race.getRaceId());

        TypeEarthDocument type1 = CreateTestTypeEarthDocument("typeEarthDocument1");
        TypeEarthDocument type2 = CreateTestTypeEarthDocument("typeEarthDocument2");

        Nation nation1 = CreateTestNation("Nation1");
        Nation nation2 = CreateTestNation("Nation2");

        List<EarthDocument> newEarthDocument = new ArrayList<EarthDocument>();
        newEarthDocument.add(CreateTestEarthDocument("testEarthName","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName2","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName3","testDescription2", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName4","testDescription", nation2.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName5","testDescription", nation1.getNationId(), type2.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName6","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien2.getPassportId()));

        //Test
        List<EarthDocument> earthDocuments = lawyerService.getEarthDocumentByFilds("", nation2.getNationId(), 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < earthDocuments.size(); i++) {
            for(int j = 0; j < newEarthDocument.size(); j++) {
                if(earthDocuments.get(i).getEarthDocumentId() == newEarthDocument.get(j).getEarthDocumentId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);

        //Delete test data
        for (EarthDocument earthDocument: newEarthDocument) {
            earthDocumentRepository.deleteById(earthDocument.getEarthDocumentId());
        }

        alienPassportRepository.deleteById(alien1.getPassportId());
        alienPassportRepository.deleteById(alien2.getPassportId());
        alienRaceRepository.deleteById(race.getRaceId());

        typeEarthDocumentRepository.deleteById(type1.getTypeEarthDocumentId());
        typeEarthDocumentRepository.deleteById(type2.getTypeEarthDocumentId());

        nationRepository.deleteById(nation1.getNationId());
        nationRepository.deleteById(nation2.getNationId());
    }

    @Test
    void checkGetEarthDocumentByFildsAny() {
        //Create test data
        AlienRace race = CreateTestRace("testRace1");
        AlienPassport alien1 = CreateTestAlien("testName", "testPlanet", "testDescription", race.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2", "testPlanet2", "testDescription2", race.getRaceId());

        TypeEarthDocument type1 = CreateTestTypeEarthDocument("typeEarthDocument1");
        TypeEarthDocument type2 = CreateTestTypeEarthDocument("typeEarthDocument2");

        Nation nation1 = CreateTestNation("Nation1");
        Nation nation2 = CreateTestNation("Nation2");

        List<EarthDocument> newEarthDocument = new ArrayList<EarthDocument>();
        newEarthDocument.add(CreateTestEarthDocument("testEarthName","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName2","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName3","testDescription2", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName4","testDescription", nation2.getNationId(), type1.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName5","testDescription", nation1.getNationId(), type2.getTypeEarthDocumentId(), alien1.getPassportId()));
        newEarthDocument.add(CreateTestEarthDocument("testEarthName6","testDescription", nation1.getNationId(), type1.getTypeEarthDocumentId(), alien2.getPassportId()));

        //Test
        List<EarthDocument> earthDocuments = lawyerService.getEarthDocumentByFilds("", nation1.getNationId(), 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < earthDocuments.size(); i++) {
            for(int j = 0; j < newEarthDocument.size(); j++) {
                if(earthDocuments.get(i).getEarthDocumentId() == newEarthDocument.get(j).getEarthDocumentId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 5);

        //Delete test data
        for (EarthDocument earthDocument: newEarthDocument) {
            earthDocumentRepository.deleteById(earthDocument.getEarthDocumentId());
        }

        alienPassportRepository.deleteById(alien1.getPassportId());
        alienPassportRepository.deleteById(alien2.getPassportId());
        alienRaceRepository.deleteById(race.getRaceId());

        typeEarthDocumentRepository.deleteById(type1.getTypeEarthDocumentId());
        typeEarthDocumentRepository.deleteById(type2.getTypeEarthDocumentId());

        nationRepository.deleteById(nation1.getNationId());
        nationRepository.deleteById(nation2.getNationId());
    }

    @Test
    void checkGetAllTypeEarthDocument() {
        int countEarthDocument = 0;
        List<EarthDocument> EarthDocumentService = lawyerService.getAllEarthDocument();

        List<EarthDocument> EarthDocumentRepository = new ArrayList<EarthDocument>();
        earthDocumentRepository.findAll().iterator().forEachRemaining(EarthDocumentRepository::add);


        for(int i = 0; i < EarthDocumentRepository.size(); i++) {
            for(int j = 0; j < EarthDocumentService.size(); j++) {
                if (EarthDocumentRepository.get(i).getEarthDocumentId() == EarthDocumentService.get(j).getEarthDocumentId()) {
                    countEarthDocument++;
                }
            }
        }

        assertTrue(countEarthDocument == EarthDocumentRepository.size());
    }

    EarthDocument CreateTestEarthDocument(String earthName, String description, Long idNation, Long idTypeDocument, Long idAlien) {
        EarthDocument doc = earthDocumentRepository.findAllByEarthName(earthName).orElse(null);
        if(doc != null) {
            earthDocumentRepository.deleteById(doc.getEarthDocumentId());
        }

        return earthDocumentRepository.save(new EarthDocument(earthName, idNation, idTypeDocument, idAlien, description));
    }

    TypeEarthDocument CreateTestTypeEarthDocument(String type) {
        TypeEarthDocument typeDoc = typeEarthDocumentRepository.findByType(type).orElse(null);

        if(typeDoc != null) {
            List<EarthDocument> buff = earthDocumentRepository.findByIdTypeDocument(typeDoc.getTypeEarthDocumentId());
            for(int i = 0; i < buff.size(); i++) {
                earthDocumentRepository.deleteById(buff.get(i).getEarthDocumentId());
            }
            typeEarthDocumentRepository.deleteById(typeDoc.getTypeEarthDocumentId());
        }
        return typeEarthDocumentRepository.save(new TypeEarthDocument(type));
    }

    Nation CreateTestNation(String name) {
        Nation nat = nationRepository.findByName(name).orElse(null);

        if(nat != null) {
            List<EarthDocument> buff = earthDocumentRepository.findByIdNation(nat.getNationId());
            for(int i = 0; i < buff.size(); i++) {
                earthDocumentRepository.deleteById(buff.get(i).getEarthDocumentId());
            }
            nationRepository.deleteById(nat.getNationId());
        }
        return nationRepository.save(new Nation(name));
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
