package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.model.researcher.SourceTechnology;
import com.itmo.mibsystem.model.researcher.Technology;
import com.itmo.mibsystem.service.PassporterService;
import com.itmo.mibsystem.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ResearcherController {
    @Autowired
    private PassporterService passporterService;
    @Autowired
    private ResearcherService researcherService;

    private Technology findFilds, addTechnology;
    List<AlienRace> race;
    List<SourceTechnology> sources;

    @ModelAttribute("alienRaces")
    public List<AlienRace> getAlienRaces() {
        race = passporterService.getAllRace();
        return race;
    }

    @ModelAttribute("sourcesTechnology")
    public List<SourceTechnology> getSourcesTechnologies() {
        sources = researcherService.getSourceTechnology();
        return sources;
    }

    @ModelAttribute("addTechnology")
    public Technology getAddTechnology() {
        return addTechnology;
    }

    @GetMapping({"/researcherIndex"})
    public ModelAndView researcherIndexGet(Model model) {
        findFilds = new Technology();
        addTechnology = new Technology();

        model.addAttribute("findFilds", findFilds);
        model.addAttribute("addTechnology", addTechnology);

        return LoadForm(findFilds, model);
    }

    @PostMapping({"/researcherIndex"})
    public ModelAndView researcherIndexPost(@ModelAttribute("findFilds") Technology findFilds, Model model) {
        return LoadForm(findFilds, model);
    }

    @PostMapping({"/addTechnology"})
    public ModelAndView addTechnologyPost(@ModelAttribute Technology addTechnology, Model model) {
        researcherService.insertTechnology(addTechnology);
        return LoadForm(findFilds, model);
    }

    @PostMapping({"/deleteTechnology"})
    public ModelAndView deleteTechnologyPost(@ModelAttribute Technology addTechnology, Model model) {
        researcherService.deleteTechnology(addTechnology);
        return LoadForm(findFilds, model);
    }

    @PostMapping({"/updateTechnology"})
    public ModelAndView updateTechnologyPost(@ModelAttribute Technology addTechnology, Model model) {
        researcherService.updateTechnology(addTechnology);
        return LoadForm(findFilds, model);
    }

    private ModelAndView LoadForm(Technology findFilds, Model model) {
        this.findFilds = findFilds;

        model.addAttribute("findFilds", findFilds);
        List<Technology> technology = researcherService.getTechnologysByFilds(findFilds.getName(), findFilds.getUse(), findFilds.getIdRace(), findFilds.getIdSource(), findFilds.getDescription());
        for(int i = 0; i < technology.size(); i ++) {
            for(int j = 0; j < race.size(); j ++) {
                if(technology.get(i).getIdRace() == race.get(j).getRaceId()) {
                    technology.get(i).setNameRace(race.get(j).getName());
                    break;
                }
            }
        }

        for(int i = 0; i < technology.size(); i ++) {
            for(int j = 0; j < sources.size(); j ++) {
                if(technology.get(i).getIdSource() == sources.get(j).getSourceId()) {
                    technology.get(i).setValueSourse(sources.get(j).getValue());
                    break;
                }
            }
        }

        model.addAttribute("technologies", technology);

        return new ModelAndView("researcher/index", model.asMap());
    }
}
