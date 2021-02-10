package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.lawyer.EarthDocumentRepository;
import com.itmo.mibsystem.dao.lawyer.NationRepository;
import com.itmo.mibsystem.dao.lawyer.TypeEarthDocumentRepository;
import com.itmo.mibsystem.model.lawyer.EarthDocument;
import com.itmo.mibsystem.model.lawyer.Nation;
import com.itmo.mibsystem.model.lawyer.TypeEarthDocument;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LawyerService {
    @Autowired
    private EarthDocumentRepository earthDocumentRepository;
    @Autowired
    private NationRepository nationRepository;
    @Autowired
    private TypeEarthDocumentRepository typeEarthDocumentRepository;

    public List<EarthDocument> getAllEarthDocument() {
        List<EarthDocument> earthDocument = new ArrayList<EarthDocument>();
        earthDocumentRepository.findAll().iterator().forEachRemaining(earthDocument::add);

        return earthDocument;
    }

    public List<Nation> getAllNation() {
        List<Nation> nation = new ArrayList<Nation>();
        nationRepository.findAll().iterator().forEachRemaining(nation::add);

        return nation;
    }

    public List<TypeEarthDocument> getAllTypeEarthDocument() {
        List<TypeEarthDocument> typeEarthDocument = new ArrayList<TypeEarthDocument>();
        typeEarthDocumentRepository.findAll().iterator().forEachRemaining(typeEarthDocument::add);

        return typeEarthDocument;
    }

    public List<EarthDocument> getEarthDocumentByFilds(String earthName, long idNation, long idTypeDocument, long idAlien, String discription) {
        return earthDocumentRepository.findEarthDocumentByEarthNameAndIdNationAndIdTypeDocumentAndidAlienAndDescription(earthName , idNation, idTypeDocument, idAlien, discription);
    }

    public EarthDocument insertEarthDocument(EarthDocument earthDocument) {

        return earthDocumentRepository.save(earthDocument);
    }

    public void deleteEarthDocument(EarthDocument earthDocument) {
        earthDocumentRepository.deleteById(earthDocument.getEarthDocumentId());
    }

    public EarthDocument updateEarthDocument(EarthDocument earthDocument){
        return earthDocumentRepository.save(earthDocument);
    }
}
