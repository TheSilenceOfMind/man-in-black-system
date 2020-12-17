package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.pasportist.AlienPassport;
import com.itmo.mibsystem.model.pasportist.AlienRace;
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
public class PassporterController {
    @Autowired
    private PassporterService passporterService;

    private AlienPassport findFilds, addPassport;
    List<AlienRace> race;

    @ModelAttribute("alienRaces")
    public List<AlienRace> getAlienRaces() {
        race = passporterService.getAllRace();
        return race;
    }

    @ModelAttribute("addPassport")
    public AlienPassport getAddPassport() {
        return addPassport;
    }

    @GetMapping({"/passporterIndex"})
    public ModelAndView passporterIndexGet(Model model) throws Exception {
        findFilds = new AlienPassport();
        addPassport = new AlienPassport();

        model.addAttribute("findFilds", findFilds);
        model.addAttribute("addPassport", addPassport);

        return LoadForm(findFilds, model);
    }

    @PostMapping({"/passporterIndex"})
    public ModelAndView passporterIndexPost(@ModelAttribute("findFilds") AlienPassport findFilds, Model model) throws Exception {
        return LoadForm(findFilds, model);
    }

    @PostMapping({"/addPassport"})
    public ModelAndView addPassportPost(@ModelAttribute AlienPassport addPassport, Model model) throws Exception {
        passporterService.insertPassport(addPassport);
        return LoadForm(findFilds, model);
    }

    @PostMapping({"/deletePassport"})
    public ModelAndView deletePassportPost(@ModelAttribute AlienPassport addPassport, Model model) throws Exception {
        passporterService.deletePassport(addPassport);
        return LoadForm(findFilds, model);
    }

    @PostMapping({"/updatePassport"})
    public ModelAndView updatePassportPost(@ModelAttribute AlienPassport addPassport, Model model) throws Exception {
        passporterService.updatePassport(addPassport);
        return LoadForm(findFilds, model);
    }

    private ModelAndView LoadForm(AlienPassport findFilds, Model model) {
        this.findFilds = findFilds;

        model.addAttribute("findFilds", findFilds);
        List<AlienPassport> alien = passporterService.getAliensByFilds(findFilds.getName(), findFilds.getHomePlanet(), findFilds.getIdRace(), findFilds.getDescription());
        for(int i = 0; i < alien.size(); i ++) {
            alien.get(i).setNameRace(race.get(alien.get(i).getIdRace().intValue() - 1).getName());
        }
        model.addAttribute("aliens", alien);

        return new ModelAndView("passporter/index", model.asMap());
    }
}
