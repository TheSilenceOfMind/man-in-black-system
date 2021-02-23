package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.distribute.technology.*;
import com.itmo.mibsystem.dao.hrmanager.MIBEmployeeRepository;
import com.itmo.mibsystem.dao.hrmanager.RoleRepository;
import com.itmo.mibsystem.dao.hrmanager.UserRepository;
import com.itmo.mibsystem.dao.pasportist.AlienPassportRepository;
import com.itmo.mibsystem.dao.pasportist.AlienRaceRepository;
import com.itmo.mibsystem.dao.researcher.SourceTechnologyRepository;
import com.itmo.mibsystem.dao.researcher.TechnologyRepository;
import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.itmo.mibsystem.model.hrmanager.FreePersona;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.model.researcher.SourceTechnology;
import com.itmo.mibsystem.model.researcher.Technology;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DistributeTechnologyServiceTests {

    private static Long deliveryTypeId;

    @BeforeAll
    static void initIds() {
        deliveryTypeId = 130L;
    }

    @Autowired
    DistributeTechnologyService distributeTechnologyService;

    @Autowired
    DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    TypeContractRepository typeContractRepository;

    @Autowired
    PaymentTypeRepository paymentTypeRepository;

    @Autowired
    SellTechnologyDocumentRepository sellTechnologyDocumentRepository;

    @Autowired
    AlienPassportRepository alienPassportRepository;

    @Autowired
    AlienRaceRepository alienRaceRepository;

    @Autowired
    BuyTechnologyDocumentRepository buyTechnologyDocumentRepository;

    @Autowired
    SourceTechnologyRepository sourceTechnologyRepository;

    @Autowired
    TechnologyRepository technologyRepository;

    @Autowired
    private MIBEmployeeRepository mIBEmployeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DistributeTechnologyItemRepository distributeTechnologyItemRepository;

    @Test
    void getAllDeliveryType_shouldFind() {
        List<DeliveryType> given = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            given.add(generateDeliveryType());
        }

        deliveryTypeRepository.saveAll(given);
        List<DeliveryType> found = distributeTechnologyService.getAllDeliveryType();

        assertTrue(containsAllDeliveryTypes(given, found));
    }

    @Test
    void checkInsertSellTechnologyDocument() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testType");
        AlienRace race = CreateTestRace("testRace");
        Technology technology = CreateTestTechnology("testName", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        AlienPassport alien = CreateTestAlien("testName","testHomePlanet", "testDescription", race.getRaceId());

        TypeContract type = CreateTestTypeContract("type");

        //Test
        SellTechnologyDocument sellTechnologyDocument = new SellTechnologyDocument("testCost", 0L, "testDescription", technology.getTechnologyId(), type.getTypeContractId(), alien.getPassportId());
        sellTechnologyDocument = distributeTechnologyService.insertSellTechnologyDocument(sellTechnologyDocument);

        SellTechnologyDocument findSellTechnologyDocument = sellTechnologyDocumentRepository.findById(sellTechnologyDocument.getSellTechnologyDocumentId()).orElse(null);

        assertTrue(sellTechnologyDocument.equals(findSellTechnologyDocument));

        //Delete test data
        sellTechnologyDocumentRepository.delete(findSellTechnologyDocument);
        typeContractRepository.delete(type);
        alienPassportRepository.delete(alien);
        technologyRepository.delete(technology);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
    }

    @Test
    void checkDeleteSellTechnologyDocument() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testType");
        AlienRace race = CreateTestRace("testRace");
        Technology technology = CreateTestTechnology("testName", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        AlienPassport alien = CreateTestAlien("testName","testHomePlanet", "testDescription", race.getRaceId());

        TypeContract type = CreateTestTypeContract("type");
        SellTechnologyDocument doc = CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription", technology.getTechnologyId(), type.getTypeContractId(), alien.getPassportId());

        //Test
        distributeTechnologyService.deleteSellTechnologyDocument(doc);

        //Delete test data
        typeContractRepository.delete(type);
        alienPassportRepository.delete(alien);
        technologyRepository.delete(technology);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
    }

    @Test
    void checkUpdateSellTechnologyDocument() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testType");
        AlienRace race = CreateTestRace("testRace");
        Technology technology1 = CreateTestTechnology("testName1", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        Technology technology2 = CreateTestTechnology("testName2", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        AlienPassport alien1 = CreateTestAlien("testName1","testHomePlanet", "testDescription", race.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2","testHomePlanet", "testDescription", race.getRaceId());

        TypeContract type1 = CreateTestTypeContract("type1");
        TypeContract type2 = CreateTestTypeContract("type2");
        SellTechnologyDocument sellTechnologyDocument = CreateTestSellTechnologyDocument("testCost1", 1L, "testDescription1", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId());

        //Test
        sellTechnologyDocument.setCostForOne("testCost1");
        sellTechnologyDocument.setCount(2L);
        sellTechnologyDocument.setDescription("testDescription2");
        sellTechnologyDocument.setIdTechnology(technology2.getTechnologyId());
        sellTechnologyDocument.setIdTypeContract(type2.getTypeContractId());
        sellTechnologyDocument.setIdAlien(alien2.getPassportId());
        SellTechnologyDocument updateSellTechnologyDocument = distributeTechnologyService.updateSellTechnologyDocument(sellTechnologyDocument);

        assertTrue(sellTechnologyDocument.equals(updateSellTechnologyDocument));

        //Delete test data
        sellTechnologyDocumentRepository.delete(updateSellTechnologyDocument);
        typeContractRepository.delete(type1);
        typeContractRepository.delete(type2);
        alienPassportRepository.delete(alien1);
        alienPassportRepository.delete(alien2);
        technologyRepository.delete(technology1);
        technologyRepository.delete(technology2);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
    }

    @Test
    void checkGetSellTechnologyDocumentByFildsAll() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testType");
        AlienRace race = CreateTestRace("testRace");
        Technology technology1 = CreateTestTechnology("testName1", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        Technology technology2 = CreateTestTechnology("testName2", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        AlienPassport alien1 = CreateTestAlien("testName1","testHomePlanet", "testDescription", race.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2","testHomePlanet", "testDescription", race.getRaceId());

        TypeContract type1 = CreateTestTypeContract("type1");
        TypeContract type2 = CreateTestTypeContract("type2");

        List<SellTechnologyDocument> newDocuments = new ArrayList<SellTechnologyDocument>();
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription1", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne2", 1L, "testDescription2", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 2L, "testDescription3", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription4", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription5", technology2.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription6", technology1.getTechnologyId(), type2.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription7", technology1.getTechnologyId(), type1.getTypeContractId(), alien2.getPassportId()));

        //Test
        List<SellTechnologyDocument> documents = distributeTechnologyService.getSellTechnologyDocumentByFilds("", 0L, 0L, 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < documents.size(); i++) {
            for(int j = 0; j < newDocuments.size(); j++) {
                if(documents.get(i).getSellTechnologyDocumentId() == newDocuments.get(j).getSellTechnologyDocumentId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 7);

        //Delete test data
        DeleteFindSellTechnologyDocument(newDocuments, type1, type2, alien1, alien2,
                technology1, technology2, race, source);
    }

    @Test
    void checkGetSellTechnologyDocumentByFildsOne() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testType");
        AlienRace race = CreateTestRace("testRace");
        Technology technology1 = CreateTestTechnology("testName1", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        Technology technology2 = CreateTestTechnology("testName2", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        AlienPassport alien1 = CreateTestAlien("testName1","testHomePlanet", "testDescription", race.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2","testHomePlanet", "testDescription", race.getRaceId());

        TypeContract type1 = CreateTestTypeContract("type1");
        TypeContract type2 = CreateTestTypeContract("type2");

        List<SellTechnologyDocument> newDocuments = new ArrayList<SellTechnologyDocument>();
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription1", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne2", 1L, "testDescription2", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 2L, "testDescription3", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription4", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription5", technology2.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription6", technology1.getTechnologyId(), type2.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription7", technology1.getTechnologyId(), type1.getTypeContractId(), alien2.getPassportId()));

        //Test
        List<SellTechnologyDocument> documents = distributeTechnologyService.getSellTechnologyDocumentByFilds("testCostForOne2", 0L, 0L, 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < documents.size(); i++) {
            for(int j = 0; j < newDocuments.size(); j++) {
                if(documents.get(i).getSellTechnologyDocumentId() == newDocuments.get(j).getSellTechnologyDocumentId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);

        //Delete test data
        DeleteFindSellTechnologyDocument(newDocuments, type1, type2, alien1, alien2,
                technology1, technology2, race, source);
    }

    @Test
    void checkGetSellTechnologyDocumentByFildsAny() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testType");
        AlienRace race = CreateTestRace("testRace");
        Technology technology1 = CreateTestTechnology("testName1", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        Technology technology2 = CreateTestTechnology("testName2", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        AlienPassport alien1 = CreateTestAlien("testName1","testHomePlanet", "testDescription", race.getRaceId());
        AlienPassport alien2 = CreateTestAlien("testName2","testHomePlanet", "testDescription", race.getRaceId());

        TypeContract type1 = CreateTestTypeContract("type1");
        TypeContract type2 = CreateTestTypeContract("type2");

        List<SellTechnologyDocument> newDocuments = new ArrayList<SellTechnologyDocument>();
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription1", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne2", 1L, "testDescription2", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 2L, "testDescription3", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription4", technology1.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription5", technology2.getTechnologyId(), type1.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription6", technology1.getTechnologyId(), type2.getTypeContractId(), alien1.getPassportId()));
        newDocuments.add(CreateTestSellTechnologyDocument("testCostForOne1", 1L, "testDescription7", technology1.getTechnologyId(), type1.getTypeContractId(), alien2.getPassportId()));

        //Test
        List<SellTechnologyDocument> documents = distributeTechnologyService.getSellTechnologyDocumentByFilds("", 1L, 0L, 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < documents.size(); i++) {
            for(int j = 0; j < newDocuments.size(); j++) {
                if(documents.get(i).getSellTechnologyDocumentId() == newDocuments.get(j).getSellTechnologyDocumentId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 6);

        //Delete test data
        DeleteFindSellTechnologyDocument(newDocuments, type1, type2, alien1, alien2,
                technology1, technology2, race, source);
    }

    @Test
    void checkGetAllSellTechnologyDocument() {
        int countSellTechnologyDocument = 0;
        List<SellTechnologyDocument> documentService = distributeTechnologyService.getAllSellTechnologyDocument();

        List<SellTechnologyDocument> documentRepository = new ArrayList<SellTechnologyDocument>();
        sellTechnologyDocumentRepository.findAll().iterator().forEachRemaining(documentRepository::add);


        for(int i = 0; i < documentRepository.size(); i++) {
            for(int j = 0; j < documentService.size(); j++) {
                if (documentRepository.get(i).getSellTechnologyDocumentId() == documentService.get(j).getSellTechnologyDocumentId()) {
                    countSellTechnologyDocument++;
                }
            }
        }

        assertTrue(countSellTechnologyDocument == documentRepository.size());
    }

    @Test
    void checkInsertBuyTechnologyDocuments() {
        //Create test data
        PaymentType typeP = CreateTestPaymentType("testPaymentType");
        DeliveryType typeD = CreateDeliveryType("testDeliveryType");
        SourceTechnology source = CreateTestSourceTechnology("testSource");
        AlienRace  race = CreateTestRace("testRace");
        Technology technology = CreateTestTechnology("testName", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());

        //Test
        BuyTechnologyDocument doc = new BuyTechnologyDocument(1L, "testDescription1", typeP.getPaymentTypeId(), typeD.getDeliveryTypeId(), technology.getTechnologyId());
        doc = distributeTechnologyService.insertBuyTechnologyDocument(doc);

        BuyTechnologyDocument findDoc = buyTechnologyDocumentRepository.findById(doc.getBuyTechnologyDocumentId()).orElse(null);

        assertTrue(doc.equals(findDoc));

        //Delete test data
        buyTechnologyDocumentRepository.delete(doc);
        technologyRepository.delete(technology);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
        deliveryTypeRepository.delete(typeD);
        paymentTypeRepository.delete(typeP);
    }

    @Test
    void checkDeleteBuyTechnologyDocuments() {
        //Create test data
        PaymentType typeP = CreateTestPaymentType("testPaymentType");
        DeliveryType typeD = CreateDeliveryType("testDeliveryType");
        SourceTechnology source = CreateTestSourceTechnology("testSource");
        AlienRace  race = CreateTestRace("testRace");
        Technology technology = CreateTestTechnology("testName", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());
        BuyTechnologyDocument doc = CreateTestBuyTechnologyDocument(1L, "testDescription1", typeP.getPaymentTypeId(), typeD.getDeliveryTypeId(), technology.getTechnologyId());

        //Test
        distributeTechnologyService.deleteBuyTechnologyDocuments(doc);

        doc = buyTechnologyDocumentRepository.findByDescription("testDescription1").orElse(null);
        assertNull(doc);

        //Delete test data
        technologyRepository.delete(technology);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
        deliveryTypeRepository.delete(typeD);
        paymentTypeRepository.delete(typeP);
    }

    @Test
    void checkUpdateBuyTechnologyDocuments() {
        //Create test data
        PaymentType typeP1 = CreateTestPaymentType("testPaymentType1");
        PaymentType typeP2 = CreateTestPaymentType("testPaymentType2");
        DeliveryType typeD1 = CreateDeliveryType("testDeliveryType1");
        DeliveryType typeD2 = CreateDeliveryType("testDeliveryType2");
        SourceTechnology source = CreateTestSourceTechnology("testSource");
        AlienRace  race = CreateTestRace("testRace");
        Technology technology1 = CreateTestTechnology("testName1", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());
        Technology technology2 = CreateTestTechnology("testName2", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());
        BuyTechnologyDocument doc = CreateTestBuyTechnologyDocument(1L, "testDescription1", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId());

        //Test
        doc.setCount(2L);
        doc.setDescription("testDescription2");
        doc.setIdPaymentType(typeP2.getPaymentTypeId());
        doc.setIdDeliveryType(typeD2.getDeliveryTypeId());
        doc.setIdTechnology(technology2.getTechnologyId());
        BuyTechnologyDocument updateDoc = distributeTechnologyService.updateBuyTechnologyDocuments(doc);

        assertTrue(updateDoc.equals(doc));

        //Delete test data
        buyTechnologyDocumentRepository.delete(doc);
        technologyRepository.delete(technology1);
        technologyRepository.delete(technology2);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
        deliveryTypeRepository.delete(typeD1);
        deliveryTypeRepository.delete(typeD2);
        paymentTypeRepository.delete(typeP1);
        paymentTypeRepository.delete(typeP2);
    }

    @Test
    void checkGetBuyTechnologyDocumentsByFildsAll() {
        //Create test data
        PaymentType typeP1 = CreateTestPaymentType("testPaymentType1");
        PaymentType typeP2 = CreateTestPaymentType("testPaymentType2");
        DeliveryType typeD1 = CreateDeliveryType("testDeliveryType1");
        DeliveryType typeD2 = CreateDeliveryType("testDeliveryType2");
        SourceTechnology source = CreateTestSourceTechnology("testSource");
        AlienRace  race = CreateTestRace("testRace");
        Technology technology1 = CreateTestTechnology("testName1", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());
        Technology technology2 = CreateTestTechnology("testName2", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());
        List<BuyTechnologyDocument> newDocuments = new ArrayList<BuyTechnologyDocument>();
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription1", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(2L, "testDescription2", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription3", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription4", typeP2.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription5", typeP1.getPaymentTypeId(), typeD2.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription6", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology2.getTechnologyId()));

        //Test
        List<BuyTechnologyDocument> documents = distributeTechnologyService.getBuyTechnologyDocumentsByFilds(0L, 0L, 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < documents.size(); i++) {
            for(int j = 0; j < newDocuments.size(); j++) {
                if(documents.get(i).getBuyTechnologyDocumentId() == newDocuments.get(j).getBuyTechnologyDocumentId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 6);

        //Delete test data
        DeleteFindBuyTechnologyDocument(newDocuments, technology1, technology2, race, source, typeD1, typeD2, typeP1, typeP2);
    }

    @Test
    void checkGetBuyTechnologyDocumentsByFildsOne() {
        //Create test data
        PaymentType typeP1 = CreateTestPaymentType("testPaymentType1");
        PaymentType typeP2 = CreateTestPaymentType("testPaymentType2");
        DeliveryType typeD1 = CreateDeliveryType("testDeliveryType1");
        DeliveryType typeD2 = CreateDeliveryType("testDeliveryType2");
        SourceTechnology source = CreateTestSourceTechnology("testSource");
        AlienRace  race = CreateTestRace("testRace");
        Technology technology1 = CreateTestTechnology("testName1", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());
        Technology technology2 = CreateTestTechnology("testName2", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());
        List<BuyTechnologyDocument> newDocuments = new ArrayList<BuyTechnologyDocument>();
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription1", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(2L, "testDescription2", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription3", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription4", typeP2.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription5", typeP1.getPaymentTypeId(), typeD2.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription6", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology2.getTechnologyId()));

        //Test
        List<BuyTechnologyDocument> documents = distributeTechnologyService.getBuyTechnologyDocumentsByFilds(2L, 0L, 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < documents.size(); i++) {
            for(int j = 0; j < newDocuments.size(); j++) {
                if(documents.get(i).getBuyTechnologyDocumentId() == newDocuments.get(j).getBuyTechnologyDocumentId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);

        //Delete test data
        DeleteFindBuyTechnologyDocument(newDocuments, technology1, technology2, race, source, typeD1, typeD2, typeP1, typeP2);
    }

    @Test
    void checkGetBuyTechnologyDocumentsByFildsAny() {
        //Create test data
        PaymentType typeP1 = CreateTestPaymentType("testPaymentType1");
        PaymentType typeP2 = CreateTestPaymentType("testPaymentType2");
        DeliveryType typeD1 = CreateDeliveryType("testDeliveryType1");
        DeliveryType typeD2 = CreateDeliveryType("testDeliveryType2");
        SourceTechnology source = CreateTestSourceTechnology("testSource");
        AlienRace  race = CreateTestRace("testRace");
        Technology technology1 = CreateTestTechnology("testName1", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());
        Technology technology2 = CreateTestTechnology("testName2", "testUse", "testDescription1", race.getRaceId(), source.getSourceId());
        List<BuyTechnologyDocument> newDocuments = new ArrayList<BuyTechnologyDocument>();
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription1", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(2L, "testDescription2", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription3", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription4", typeP2.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription5", typeP1.getPaymentTypeId(), typeD2.getDeliveryTypeId(), technology1.getTechnologyId()));
        newDocuments.add(CreateTestBuyTechnologyDocument(1L, "testDescription6", typeP1.getPaymentTypeId(), typeD1.getDeliveryTypeId(), technology2.getTechnologyId()));

        //Test
        List<BuyTechnologyDocument> documents = distributeTechnologyService.getBuyTechnologyDocumentsByFilds(0L, 0L, typeP1.getPaymentTypeId(), 0L, "");
        int countFind = 0;
        for(int i = 0; i < documents.size(); i++) {
            for(int j = 0; j < newDocuments.size(); j++) {
                if(documents.get(i).getBuyTechnologyDocumentId() == newDocuments.get(j).getBuyTechnologyDocumentId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 5);

        //Delete test data
        DeleteFindBuyTechnologyDocument(newDocuments, technology1, technology2, race, source, typeD1, typeD2, typeP1, typeP2);
    }

    @Test
    void checkGetAllBuyTechnologyDocuments() {
        int countDocument = 0;
        List<BuyTechnologyDocument> documentService = distributeTechnologyService.getAllBuyTechnologyDocument();

        List<BuyTechnologyDocument> documentRepository = new ArrayList<BuyTechnologyDocument>();
        buyTechnologyDocumentRepository.findAll().iterator().forEachRemaining(documentRepository::add);


        for(int i = 0; i < documentRepository.size(); i++) {
            for(int j = 0; j < documentService.size(); j++) {
                if (documentRepository.get(i).getBuyTechnologyDocumentId() == documentService.get(j).getBuyTechnologyDocumentId()) {
                    countDocument++;
                }
            }
        }

        assertTrue(countDocument == documentRepository.size());
    }


    @Test
    void checkInsertDistributeTechnologyItem() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testValue");
        AlienRace race = CreateTestRace("testName");
        Technology tech = CreateTestTechnology("testName", "testUse", "testDescription", race.getRaceId(), source.getSourceId());

        Role role = CreateTestRole("testRole");
        MIBEmployee employee = CreateTestMIBEmployee("testName","10 age", "testDescription", "testUsername", "testPassword", (long)role.getRoleId());

        //Test
        DistributeTechnologyItem item = new DistributeTechnologyItem(1L,"testUse", tech.getTechnologyId(), employee.getMIBEmployeeId(), "testDescription1");
        item = distributeTechnologyService.insertDistributeTechnologyItem(item);

        DistributeTechnologyItem findItem = distributeTechnologyItemRepository.findAllByDescription("testDescription1").orElse(null);;

        assertTrue(item.equals(findItem));

        //Delete test data
        distributeTechnologyItemRepository.delete(item);
        mIBEmployeeRepository.delete(employee);
        userService.deleteUserById(userRepository.findByUsername("testUsername").get().getUserId());
        roleRepository.deleteByRoleId(role.getRoleId());
        technologyRepository.delete(tech);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
    }

    @Test
    void checkDeleteDistributeTechnologyItem() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testValue");
        AlienRace race = CreateTestRace("testName");
        Technology tech = CreateTestTechnology("testName", "testUse", "testDescription", race.getRaceId(), source.getSourceId());

        Role role = CreateTestRole("testRole");
        MIBEmployee employee = CreateTestMIBEmployee("testName","10 age", "testDescription", "testUsername", "testPassword", (long)role.getRoleId());

        DistributeTechnologyItem item = CreateTestDistributeTechnologyItem(1L,"testUse", tech.getTechnologyId(), employee.getMIBEmployeeId(), "testDescription1");

        //Test
        distributeTechnologyService.deleteDistributeTechnologyItem(item);

        item = distributeTechnologyItemRepository.findAllByDescription("testDescription1").orElse(null);

        assertNull(item);

        //Delete test data
        mIBEmployeeRepository.delete(employee);
        userService.deleteUserById(userRepository.findByUsername("testUsername").get().getUserId());
        roleRepository.deleteByRoleId(role.getRoleId());
        technologyRepository.delete(tech);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
    }

    @Test
    void checkUpdateDistributeTechnologyItem() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testValue");
        AlienRace race = CreateTestRace("testName");
        Technology tech1 = CreateTestTechnology("testName1", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        Technology tech2 = CreateTestTechnology("testName2", "testUse", "testDescription", race.getRaceId(), source.getSourceId());

        Role role = CreateTestRole("testRole");
        MIBEmployee employee1 = CreateTestMIBEmployee("testName1","10 age", "testDescription", "testUsername1", "testPassword", (long)role.getRoleId());
        MIBEmployee employee2 = CreateTestMIBEmployee("testName2","10 age", "testDescription", "testUsername2", "testPassword", (long)role.getRoleId());

        DistributeTechnologyItem item = CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription1");

        //Test
        item.setCount(2L);
        item.setUse("testUse2");
        item.setDescription("testDescription1");
        item.setIdTechnology(tech2.getTechnologyId());
        item.setIdAgent(employee2.getMIBEmployeeId());
        DistributeTechnologyItem updateItem = distributeTechnologyService.updateDistributeTechnologyItem(item);

        assertTrue(updateItem.equals(item));

        //Delete test data
        distributeTechnologyItemRepository.delete(item);
        mIBEmployeeRepository.delete(employee1);
        mIBEmployeeRepository.delete(employee2);
        userService.deleteUserById(userRepository.findByUsername("testUsername1").get().getUserId());
        userService.deleteUserById(userRepository.findByUsername("testUsername2").get().getUserId());
        roleRepository.deleteByRoleId(role.getRoleId());
        technologyRepository.delete(tech1);
        technologyRepository.delete(tech2);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
    }

    @Test
    void checkGetDistributeTechnologyItemByFildsAll() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testValue");
        AlienRace race = CreateTestRace("testName");
        Technology tech1 = CreateTestTechnology("testName1", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        Technology tech2 = CreateTestTechnology("testName2", "testUse", "testDescription", race.getRaceId(), source.getSourceId());

        Role role = CreateTestRole("testRole");
        MIBEmployee employee1 = CreateTestMIBEmployee("testName1","10 age", "testDescription", "testUsername1", "testPassword", (long)role.getRoleId());
        MIBEmployee employee2 = CreateTestMIBEmployee("testName2","10 age", "testDescription", "testUsername2", "testPassword", (long)role.getRoleId());

        List<DistributeTechnologyItem> newItems = new ArrayList<DistributeTechnologyItem>();
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription1"));
        newItems.add(CreateTestDistributeTechnologyItem(2L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription2"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse2", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription3"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech2.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription4"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee2.getMIBEmployeeId(), "testDescription5"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription6"));

        //Test
        List<DistributeTechnologyItem> items = distributeTechnologyService.getDistributeTechnologyItemByFilds(0L, "", 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < items.size(); i++) {
            for(int j = 0; j < newItems.size(); j++) {
                if(items.get(i).getDistributeTechnologyItemId() == newItems.get(j).getDistributeTechnologyItemId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 6);

        //Delete test data
        DeleteFindDistributeTechnologyItem(newItems, employee1, employee2, employee1.getUsername(), employee2.getUsername(), role, tech1, tech2, race, source);
    }

    @Test
    void checkGetDistributeTechnologyItemByFildsOne() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testValue");
        AlienRace race = CreateTestRace("testName");
        Technology tech1 = CreateTestTechnology("testName1", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        Technology tech2 = CreateTestTechnology("testName2", "testUse", "testDescription", race.getRaceId(), source.getSourceId());

        Role role = CreateTestRole("testRole");
        MIBEmployee employee1 = CreateTestMIBEmployee("testName1","10 age", "testDescription", "testUsername1", "testPassword", (long)role.getRoleId());
        MIBEmployee employee2 = CreateTestMIBEmployee("testName2","10 age", "testDescription", "testUsername2", "testPassword", (long)role.getRoleId());

        List<DistributeTechnologyItem> newItems = new ArrayList<DistributeTechnologyItem>();
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription1"));
        newItems.add(CreateTestDistributeTechnologyItem(2L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription2"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse2", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription3"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech2.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription4"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee2.getMIBEmployeeId(), "testDescription5"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription6"));

        //Test
        List<DistributeTechnologyItem> items = distributeTechnologyService.getDistributeTechnologyItemByFilds(2L, "", 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < items.size(); i++) {
            for(int j = 0; j < newItems.size(); j++) {
                if(items.get(i).getDistributeTechnologyItemId() == newItems.get(j).getDistributeTechnologyItemId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);

        //Delete test data
        DeleteFindDistributeTechnologyItem(newItems, employee1, employee2, employee1.getUsername(), employee2.getUsername(), role, tech1, tech2, race, source);
    }

    @Test
    void checkGetDistributeTechnologyItemByFildsAny() {
        //Create test data
        SourceTechnology source = CreateTestSourceTechnology("testValue");
        AlienRace race = CreateTestRace("testName");
        Technology tech1 = CreateTestTechnology("testName1", "testUse", "testDescription", race.getRaceId(), source.getSourceId());
        Technology tech2 = CreateTestTechnology("testName2", "testUse", "testDescription", race.getRaceId(), source.getSourceId());

        Role role = CreateTestRole("testRole");
        MIBEmployee employee1 = CreateTestMIBEmployee("testName1","10 age", "testDescription", "testUsername1", "testPassword", (long)role.getRoleId());
        MIBEmployee employee2 = CreateTestMIBEmployee("testName2","10 age", "testDescription", "testUsername2", "testPassword", (long)role.getRoleId());

        List<DistributeTechnologyItem> newItems = new ArrayList<DistributeTechnologyItem>();
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription1"));
        newItems.add(CreateTestDistributeTechnologyItem(2L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription2"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse2", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription3"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech2.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription4"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee2.getMIBEmployeeId(), "testDescription5"));
        newItems.add(CreateTestDistributeTechnologyItem(1L,"testUse1", tech1.getTechnologyId(), employee1.getMIBEmployeeId(), "testDescription6"));

        //Test
        List<DistributeTechnologyItem> items = distributeTechnologyService.getDistributeTechnologyItemByFilds(0L, "testUse1", 0L, 0L, "");
        int countFind = 0;
        for(int i = 0; i < items.size(); i++) {
            for(int j = 0; j < newItems.size(); j++) {
                if(items.get(i).getDistributeTechnologyItemId() == newItems.get(j).getDistributeTechnologyItemId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 5);

        //Delete test data
        DeleteFindDistributeTechnologyItem(newItems, employee1, employee2, employee1.getUsername(), employee2.getUsername(), role, tech1, tech2, race, source);
    }

    @Test
    void checkGetAllDistributeTechnologyItem() {
        int countItems = 0;
        List<DistributeTechnologyItem> itemsService = distributeTechnologyService.getAllDistributeTechnologyItem();

        List<DistributeTechnologyItem> itemsRepository = new ArrayList<DistributeTechnologyItem>();
        distributeTechnologyItemRepository.findAll().iterator().forEachRemaining(itemsRepository::add);


        for(int i = 0; i < itemsRepository.size(); i++) {
            for(int j = 0; j < itemsService.size(); j++) {
                if (itemsRepository.get(i).getDistributeTechnologyItemId() == itemsService.get(j).getDistributeTechnologyItemId()) {
                    countItems++;
                }
            }
        }

        assertTrue(countItems == itemsRepository.size());
    }


    @Test
    void checkDeleteBuyTechnologyMarketById() {
        //Create test data
        ArrayList<BuyTechnologyMarket> buyTechnologys = new ArrayList<BuyTechnologyMarket>();
        buyTechnologys.add(new BuyTechnologyMarket(1L, "testUse", 1L, 1L, 1L, "testDescription"));

        //Test
        distributeTechnologyService.deleteBuyTechnologyMarketById(buyTechnologys, 1L);

        assertTrue(buyTechnologys.size() == 0);
    }

    @Test
    void checkGetBuyTechnologyMarketByFildsAll() {
        //Create test data
        ArrayList<BuyTechnologyMarket> newBuyTechnologys = new ArrayList<BuyTechnologyMarket>();
        newBuyTechnologys.add(new BuyTechnologyMarket(1L, "testUse1", 1L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(2L, "testUse1", 1L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(3L, "testUse2", 1L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(4L, "testUse1", 2L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(5L, "testUse1", 1L, 2L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(6L, "testUse1", 1L, 1L, 2L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(7L, "testUse1", 1L, 1L, 2L, "testDescription2"));

        //Test
        List<BuyTechnologyMarket> buyTechnologys = distributeTechnologyService.getBuyTechnologyMarketByFilds(new BuyTechnologyMarket(0L, "", 0L, 0L, 0L, ""), newBuyTechnologys);
        int countFind = 0;
        for(int i = 0; i < buyTechnologys.size(); i++) {
            for(int j = 0; j < newBuyTechnologys.size(); j++) {
                if(buyTechnologys.get(i).getBuyTechnologyMarketId() == newBuyTechnologys.get(j).getBuyTechnologyMarketId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 7);
    }

    @Test
    void checkGetBuyTechnologyMarketByFildsOne() {
        //Create test data
        ArrayList<BuyTechnologyMarket> newBuyTechnologys = new ArrayList<BuyTechnologyMarket>();
        newBuyTechnologys.add(new BuyTechnologyMarket(1L, "testUse1", 1L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(2L, "testUse1", 1L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(3L, "testUse2", 1L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(4L, "testUse1", 2L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(5L, "testUse1", 1L, 2L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(6L, "testUse1", 1L, 1L, 2L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(7L, "testUse1", 1L, 1L, 2L, "testDescription2"));

        //Test
        List<BuyTechnologyMarket> buyTechnologys = distributeTechnologyService.getBuyTechnologyMarketByFilds(new BuyTechnologyMarket(0L, "testUse2", 0L, 0L, 0L, ""), newBuyTechnologys);
        int countFind = 0;
        for(int i = 0; i < buyTechnologys.size(); i++) {
            for(int j = 0; j < newBuyTechnologys.size(); j++) {
                if(buyTechnologys.get(i).getBuyTechnologyMarketId() == newBuyTechnologys.get(j).getBuyTechnologyMarketId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);
    }

    @Test
    void checkGetBuyTechnologyMarketByFildsAny() {
        //Create test data
        ArrayList<BuyTechnologyMarket> newBuyTechnologys = new ArrayList<BuyTechnologyMarket>();
        newBuyTechnologys.add(new BuyTechnologyMarket(1L, "testUse1", 1L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(2L, "testUse1", 1L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(3L, "testUse2", 1L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(4L, "testUse1", 2L, 1L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(5L, "testUse1", 1L, 2L, 1L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(6L, "testUse1", 1L, 1L, 2L, "testDescription1"));
        newBuyTechnologys.add(new BuyTechnologyMarket(7L, "testUse1", 1L, 1L, 1L, "testDescription2"));

        //Test
        List<BuyTechnologyMarket> buyTechnologys = distributeTechnologyService.getBuyTechnologyMarketByFilds(new BuyTechnologyMarket(0L, "", 0L, 0L, 1L, ""), newBuyTechnologys);
        int countFind = 0;
        for(int i = 0; i < buyTechnologys.size(); i++) {
            for(int j = 0; j < newBuyTechnologys.size(); j++) {
                if(buyTechnologys.get(i).getBuyTechnologyMarketId() == newBuyTechnologys.get(j).getBuyTechnologyMarketId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 6);
    }

    private static DeliveryType generateDeliveryType() {
        deliveryTypeId++;
        return new DeliveryType(deliveryTypeId, "type" + deliveryTypeId);
    }

    private boolean containsAllDeliveryTypes(List<DeliveryType> given, List<DeliveryType> found) {
        List<String> givenTypes = given.stream().map(DeliveryType::getType).collect(Collectors.toList());
        List<String> foundTypes = found.stream()
                .map(DeliveryType::getType)
                .filter( it -> givenTypes.contains(it) )
                .collect(Collectors.toList());
        return foundTypes.size() == givenTypes.size();
    }

    void DeleteFindSellTechnologyDocument(List<SellTechnologyDocument> newDocuments, TypeContract type1,
                                          TypeContract type2, AlienPassport alien1, AlienPassport alien2,
                                          Technology technology1, Technology technology2,
                                          AlienRace race, SourceTechnology source) {
        for (SellTechnologyDocument document: newDocuments) {
            sellTechnologyDocumentRepository.deleteById(document.getSellTechnologyDocumentId());
        }
        typeContractRepository.delete(type1);
        typeContractRepository.delete(type2);
        alienPassportRepository.delete(alien1);
        alienPassportRepository.delete(alien2);
        technologyRepository.delete(technology1);
        technologyRepository.delete(technology2);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
    }

    void DeleteFindBuyTechnologyDocument(List<BuyTechnologyDocument> newDocuments, Technology technology1,
                                         Technology technology2, AlienRace race, SourceTechnology source,
                                         DeliveryType typeD1, DeliveryType typeD2, PaymentType typeP1, PaymentType typeP2) {
        for (BuyTechnologyDocument document: newDocuments) {
            buyTechnologyDocumentRepository.deleteById(document.getBuyTechnologyDocumentId());
        }
        technologyRepository.delete(technology1);
        technologyRepository.delete(technology2);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
        deliveryTypeRepository.delete(typeD1);
        deliveryTypeRepository.delete(typeD2);
        paymentTypeRepository.delete(typeP1);
        paymentTypeRepository.delete(typeP2);
    }

    void DeleteFindDistributeTechnologyItem(List<DistributeTechnologyItem> newItems, MIBEmployee employee1,
                                            MIBEmployee employee2, String username1, String username2,
                                            Role role, Technology tech1, Technology tech2, AlienRace race,
                                            SourceTechnology source) {
        for (DistributeTechnologyItem item: newItems) {
            distributeTechnologyItemRepository.deleteById(item.getDistributeTechnologyItemId());
        }
        mIBEmployeeRepository.delete(employee1);
        mIBEmployeeRepository.delete(employee2);
        userService.deleteUserById(userRepository.findByUsername(username1).get().getUserId());
        userService.deleteUserById(userRepository.findByUsername(username2).get().getUserId());
        roleRepository.deleteByRoleId(role.getRoleId());
        technologyRepository.delete(tech1);
        technologyRepository.delete(tech2);
        alienRaceRepository.delete(race);
        sourceTechnologyRepository.delete(source);
    }

    TypeContract CreateTestTypeContract(String type) {
        TypeContract typeC = typeContractRepository.findAllByType(type).orElse(null);

        if(typeC != null) {
            List<SellTechnologyDocument> buff = sellTechnologyDocumentRepository.findByIdTypeContract(typeC.getTypeContractId());
            for(int i = 0; i < buff.size(); i++) {
                sellTechnologyDocumentRepository.deleteById(buff.get(i).getSellTechnologyDocumentId());
            }
            typeContractRepository.deleteById(typeC.getTypeContractId());
        }
        return typeContractRepository.save(new TypeContract(type));
    }

    PaymentType CreateTestPaymentType(String type) {
        PaymentType typeP = paymentTypeRepository.findAllByType(type).orElse(null);

        if(typeP != null) {
            List<BuyTechnologyDocument> buff = buyTechnologyDocumentRepository.findByIdPaymentType(typeP.getPaymentTypeId());
            for(int i = 0; i < buff.size(); i++) {
                buyTechnologyDocumentRepository.deleteById(buff.get(i).getBuyTechnologyDocumentId());
            }
            paymentTypeRepository.deleteById(typeP.getPaymentTypeId());
        }
        return paymentTypeRepository.save(new PaymentType(type));
    }

    DeliveryType CreateDeliveryType(String type) {
        DeliveryType typeD = deliveryTypeRepository.findAllByType(type).orElse(null);

        if(typeD != null) {
            List<BuyTechnologyDocument> buff = buyTechnologyDocumentRepository.findByIdDeliveryType(typeD.getDeliveryTypeId());
            for(int i = 0; i < buff.size(); i++) {
                buyTechnologyDocumentRepository.deleteById(buff.get(i).getBuyTechnologyDocumentId());
            }
            deliveryTypeRepository.deleteById(typeD.getDeliveryTypeId());
        }
        return deliveryTypeRepository.save(new DeliveryType(type));
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

    AlienPassport CreateTestAlien(String name, String homePlane, String description, Long idRace) {
        AlienPassport pass = alienPassportRepository.findByName(name).orElse(null);
        if(pass != null) {
            alienPassportRepository.deleteById(pass.getPassportId());
        }

        return alienPassportRepository.save(new AlienPassport(null, name, homePlane, description, idRace, false));
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


    Technology CreateTestTechnology(String name, String use, String description, Long idRace, Long idSource) {
        Technology pass = technologyRepository.findByName(name).orElse(null);
        if(pass != null) {
            technologyRepository.deleteById(pass.getTechnologyId());
        }

        return technologyRepository.save(new Technology(name, use, description, idRace, idSource));
    }

    SellTechnologyDocument CreateTestSellTechnologyDocument(String costForOne, Long count, String description, Long idTechnology, Long idTypeContract, Long idAlien) {
        SellTechnologyDocument doc = sellTechnologyDocumentRepository.findByDescription(description).orElse(null);
        if(doc != null) {
            sellTechnologyDocumentRepository.deleteById(doc.getSellTechnologyDocumentId());
        }

        return sellTechnologyDocumentRepository.save(new SellTechnologyDocument(costForOne, count, description, idTechnology, idTypeContract, idAlien));
    }

    BuyTechnologyDocument CreateTestBuyTechnologyDocument(Long count, String description, Long idPaymentType, Long idDeliveryType, Long idTechnology){
        BuyTechnologyDocument doc = buyTechnologyDocumentRepository.findByDescription(description).orElse(null);
        if(doc != null) {
            buyTechnologyDocumentRepository.deleteById(doc.getBuyTechnologyDocumentId());
        }

        return buyTechnologyDocumentRepository.save(new BuyTechnologyDocument(count, description, idPaymentType, idDeliveryType, idTechnology));
    }

    Role CreateTestRole(String roleName) {
        Role role = roleRepository.findByRoleName(roleName).orElse(null);

        if(role != null) {
            List<User> buff = userRepository.findAllByRoles(role.getRoleName());
            for(int i = 0; i < buff.size(); i++) {
                userRepository.deleteById(buff.get(i).getUserId());
            }
            roleRepository.deleteByRoleId(role.getRoleId());
        }
        return roleRepository.save(new Role(roleName));
    }

    MIBEmployee CreateTestMIBEmployee(String name, String age, String description, String username, String password, Long idRole) {
        MIBEmployee employee = mIBEmployeeRepository.findAllByName(name).orElse(null);
        if(employee != null) {
            mIBEmployeeRepository.deleteById(employee.getMIBEmployeeId());
        }

        User buffUser = userService.saveUser(new User(0L, username, password, false), idRole, true);
        MIBEmployee buff = new MIBEmployee(name, age, description, username, password, idRole, null);
        buff.setIdUser(buffUser.getUserId());

        return mIBEmployeeRepository.save(buff);
    }

    DistributeTechnologyItem CreateTestDistributeTechnologyItem(Long count, String use, Long idTechnology, Long idAgent, String description) {
        DistributeTechnologyItem item = distributeTechnologyItemRepository.findAllByDescription(description).orElse(null);
        if(item != null) {
            distributeTechnologyItemRepository.deleteById(item.getDistributeTechnologyItemId());
        }

        return distributeTechnologyItemRepository.save(new DistributeTechnologyItem(count, use, idTechnology, idAgent, description));
    }

}
