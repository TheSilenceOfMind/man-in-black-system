package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.model.lawyer.EarthDocument;
import com.itmo.mibsystem.model.lawyer.Nation;
import com.itmo.mibsystem.model.lawyer.TypeEarthDocument;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.service.LawyerService;
import com.itmo.mibsystem.service.PassporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LawyerController {
    @Autowired
    private PassporterService passporterService;

    @Autowired
    private LawyerService lawyerService;

    private AlienPassport findFildsPassport;
    private EarthDocument findFildsLawyer, addEarthDocument;
    List<AlienRace> race;
    List<Nation> nation;
    List<AlienPassport> listAliens;
    List<TypeEarthDocument> typeEarthDocument;

    @ModelAttribute("alienRaces")
    public List<AlienRace> getAlienRaces() {
        race = passporterService.getAllRace();
        return race;
    }

    @ModelAttribute("nations")
    public List<Nation> getNation() {
        nation = lawyerService.getAllNation();
        return nation;
    }

    @ModelAttribute("listAliens")
    public List<AlienPassport> getAliens() {
        listAliens = passporterService.getAliens();
        return listAliens;
    }

    @ModelAttribute("typeEarthDocuments")
    public List<TypeEarthDocument> getTypeEarthDocument() {
        typeEarthDocument = lawyerService.getAllTypeEarthDocument();
        return typeEarthDocument;
    }

    @ModelAttribute("addEarthDocument")
    public EarthDocument getAddEarthDocument() {
        return addEarthDocument;
    }

    @GetMapping({"/lawyerIndex"})
    public ModelAndView lawyerIndexGet(Model model) {
        findFildsPassport = new AlienPassport();
        findFildsLawyer = new EarthDocument();
        addEarthDocument = new EarthDocument();

        model.addAttribute("findFildsPassport", findFildsPassport);
        model.addAttribute("findFildsLawyer", findFildsLawyer);
        model.addAttribute("addEarthDocument", addEarthDocument);

        return LoadForm(findFildsPassport, findFildsLawyer, model);
    }

    @PostMapping({"/lawyerIndex"})
    public ModelAndView lawyerIndexPost(@ModelAttribute("findFildsPassport") AlienPassport findFildsPassport, @ModelAttribute("findFildsLawyer") EarthDocument findFildsLawyer, Model model) {
        return LoadForm(findFildsPassport, findFildsLawyer, model);
    }

    @PostMapping({"/addEarthDocument"})
    public ModelAndView addEarthDocumentPost(@ModelAttribute EarthDocument addEarthDocument, Model model) {
        lawyerService.insertEarthDocument(addEarthDocument);
        return LoadForm(findFildsPassport, findFildsLawyer, model);
    }

    @PostMapping({"/deleteEarthDocument"})
    public ModelAndView deleteEarthDocumentPost(@ModelAttribute EarthDocument addEarthDocument, Model model) {
        lawyerService.deleteEarthDocument(addEarthDocument);
        return LoadForm(findFildsPassport, findFildsLawyer, model);
    }

    @PostMapping({"/updateEarthDocument"})
    public ModelAndView updateEarthDocumentPost(@ModelAttribute EarthDocument addEarthDocument, Model model) {
        lawyerService.updateEarthDocument(addEarthDocument);
        return LoadForm(findFildsPassport, findFildsLawyer, model);
    }

    private ModelAndView LoadForm(AlienPassport findFildsPassport, EarthDocument findFildsLawyer, Model model) {
        this.findFildsPassport = findFildsPassport;
        this.findFildsLawyer = findFildsLawyer;

        model.addAttribute("findFildsPassport", findFildsPassport);
        model.addAttribute("findFildsLawyer", findFildsLawyer);

        List<AlienPassport> alien = passporterService.getAliensByFilds(findFildsPassport.getName(), findFildsPassport.getHomePlanet(), findFildsPassport.getIdRace(), findFildsPassport.getDescription());
        for(int i = 0; i < alien.size(); i ++) {
            for(int j = 0; j < race.size(); j ++) {
                if(alien.get(i).getIdRace() == race.get(j).getRaceId()) {
                    alien.get(i).setNameRace(race.get(j).getName());
                    break;
                }
            }
        }

        model.addAttribute("aliens", alien);

        List<EarthDocument> earthDocument = lawyerService.getEarthDocumentByFilds(findFildsLawyer.getEarthName(), findFildsLawyer.getIdNation(), findFildsLawyer.getIdTypeDocument(), findFildsLawyer.getIdAlien(), findFildsLawyer.getDescription());
        List<AlienPassport> buffAlien = passporterService.getAliens();
        for(int i = 0; i < earthDocument.size(); i ++) {
            for(int j = 0; j < nation.size(); j ++) {
                if(earthDocument.get(i).getIdNation().equals(nation.get(j).getNationId())) {
                    earthDocument.get(i).setNameNation(nation.get(j).getName());
                    break;
                }
            }

            for(int j = 0; j < typeEarthDocument.size(); j ++) {
                if(earthDocument.get(i).getIdTypeDocument().equals(typeEarthDocument.get(j).getTypeEarthDocumentId())) {
                    earthDocument.get(i).setNameTypeDocument(typeEarthDocument.get(j).getType());
                    break;
                }
            }

            for(int j = 0; j < buffAlien.size(); j ++) {
                if(earthDocument.get(i).getIdAlien().equals(buffAlien.get(j).getPassportId())) {
                    earthDocument.get(i).setNameAlien(buffAlien.get(j).getName());
                    break;
                }
            }
        }

        model.addAttribute("earthDocuments", earthDocument);

        return new ModelAndView("lawyer/index", model.asMap());
    }
}
