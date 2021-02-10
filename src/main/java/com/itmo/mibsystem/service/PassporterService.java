package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.pasportist.AlienPassportRepository;
import com.itmo.mibsystem.dao.pasportist.AlienRaceRepository;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassporterService {

    @Autowired
    private AlienPassportRepository alienPassportRepository;

    @Autowired
    private AlienRaceRepository alienRaceRepository;

    public List<AlienRace> getAllRace() {
        List<AlienRace> alienRaces = new ArrayList<AlienRace>();
        alienRaceRepository.findAll().iterator().forEachRemaining(alienRaces::add);

        return alienRaces;
    }

    public List<AlienPassport> getAliens() {
        List<AlienPassport> aliens = new ArrayList<AlienPassport>();
        alienPassportRepository.findAll().iterator().forEachRemaining(aliens::add);

        return aliens;
    }

    public List<AlienPassport> getAliensByFilds(String name, String homePlanet, long idRace, String discription) {
        return alienPassportRepository.findAlienPassportByNameAndHomePlanetAndIdRaceAndDescription(name , homePlanet, idRace, discription);
    }

    public AlienPassport insertPassport(AlienPassport alienPassport) {
        return alienPassportRepository.save(alienPassport);
    }

    public void deletePassport(AlienPassport alienPassport) {
        alienPassportRepository.deleteById(alienPassport.getPassportId());
    }

    public AlienPassport updatePassport(AlienPassport alienPassport){
        return alienPassportRepository.save(alienPassport);
    }

}
