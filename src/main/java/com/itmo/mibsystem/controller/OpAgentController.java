package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import com.itmo.mibsystem.model.op_agent.ActDetention;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.service.HrManagerService;
import com.itmo.mibsystem.service.OpAgentService;
import com.itmo.mibsystem.service.PassporterService;
import com.itmo.mibsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OpAgentController {
    @Autowired
    private OpAgentService opAgentService;

    @Autowired
    private PassporterService passporterService;

    @Autowired
    private HrManagerService hrManagerService;

    private ActDetention findFilds, addActDetention;
    List<AlienPassport> aliens;
    List<MIBEmployee> users;

    @ModelAttribute("aliens")
    public List<AlienPassport> getAliens() {
        aliens = passporterService.getAliens();
        return aliens;
    }

    @ModelAttribute("agents")
    public List<MIBEmployee> getUsers() {
        users = hrManagerService.getAllMIBEmployee();
        return users;
    }

    @ModelAttribute("addActDetention")
    public ActDetention getAddActDetention() {
        return addActDetention;
    }

    @GetMapping({"/agentIndex"})
    public ModelAndView opAgentIndexGet(Model model) {
        findFilds = new ActDetention();
        addActDetention = new ActDetention();

        model.addAttribute("findFilds", findFilds);
        model.addAttribute("addActDetention", addActDetention);

        return LoadForm(findFilds, model);
    }

    @PostMapping({"/agentIndex"})
    public ModelAndView opAgentIndexPost(@ModelAttribute("findFilds") ActDetention findFilds, Model model) {
        return LoadForm(findFilds, model);
    }

    @PostMapping({"/addActDetention"})
    public ModelAndView addActDetentionPost(@ModelAttribute ActDetention addActDetention, Model model) {
        opAgentService.insertActDetention(addActDetention);
        return LoadForm(findFilds, model);
    }

    @PostMapping({"/deleteActDetention"})
    public ModelAndView deleteActDetentionPost(@ModelAttribute ActDetention addActDetention, Model model) {
        opAgentService.deleteActDetention(addActDetention);
        return LoadForm(findFilds, model);
    }

    @PostMapping({"/updateActDetention"})
    public ModelAndView updateActDetention(@ModelAttribute ActDetention addActDetention, Model model) {
        opAgentService.updateActDetention(addActDetention);
        return LoadForm(findFilds, model);
    }

    private ModelAndView LoadForm(ActDetention findFilds, Model model) {
        this.findFilds = findFilds;

        model.addAttribute("findFilds", findFilds);
        List<ActDetention> actDetention = opAgentService.getActDetentionByFilds(findFilds.getScene(), findFilds.getIdGuiltyAlien(), findFilds.getIdUserAgent(), findFilds.getDescription());
        for(int i = 0; i < actDetention.size(); i ++) {
            for(int j = 0; j < aliens.size(); j ++) {
                if(actDetention.get(i).getIdGuiltyAlien().equals(aliens.get(j).getPassportId())) {
                    actDetention.get(i).setNameAlien(aliens.get(j).getName());
                    break;
                }
            }

            for(int j = 0; j < users.size(); j ++) {
                if(actDetention.get(i).getIdUserAgent().equals(users.get(j).getMIBEmployeeId())) {
                    actDetention.get(i).setNameAgent(users.get(j).getName());
                    break;
                }
            }
        }

        model.addAttribute("acts", actDetention);

        return new ModelAndView("agent/index", model.asMap());
    }
}
