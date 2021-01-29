package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.researcher.SourceTechnologyRepository;
import com.itmo.mibsystem.dao.researcher.TechnologyRepository;
import com.itmo.mibsystem.model.distribute.technology.BuyTechnologyDocument;
import com.itmo.mibsystem.model.researcher.SourceTechnology;
import com.itmo.mibsystem.model.researcher.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResearcherService {
    @Autowired
    private SourceTechnologyRepository sourceTechnologyRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private DistributeTechnologyService distributeTechnologyService;


    public List<SourceTechnology> getSourceTechnology() {
        List<SourceTechnology> sourceTechnology = new ArrayList<SourceTechnology>();
        sourceTechnologyRepository.findAll().iterator().forEachRemaining(sourceTechnology::add);

        return sourceTechnology;
    }

    public List<Technology> getTechnologies() {
        List<Technology> technologies = new ArrayList<Technology>();
        technologyRepository.findAll().iterator().forEachRemaining(technologies::add);

        return technologies;
    }

    public List<Technology> getAliensByFilds(String name, String use, long idRace, long idSource, String discription) {
        return technologyRepository.findTechnologyByNameAndUseAndIdRaceAAndIdSourceAndDescription(name , use, idRace, idSource, discription);
    }

    public Technology insertTechnology(Technology technology) {
        return technologyRepository.save(technology);
    }

    public void deleteTechnology(Technology technology) {
        List<BuyTechnologyDocument>  buyTechnologyDocuments = distributeTechnologyService.getAllBuyTechnologyDocument();

        for(int i = 0; i < buyTechnologyDocuments.size(); i++) {
            if(buyTechnologyDocuments.get(i).getIdTechnology() == technology.getTechnologyId()) {
                distributeTechnologyService.deleteBuyTechnologyDocuments(buyTechnologyDocuments.get(i));
            }
        }

        technologyRepository.deleteById(technology.getTechnologyId());
    }

    public void updateTechnology(Technology technology){
        technologyRepository.save(technology);
    }
}
