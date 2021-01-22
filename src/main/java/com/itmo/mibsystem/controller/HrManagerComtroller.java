package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.hrmanager.FreePersona;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import com.itmo.mibsystem.service.HrManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HrManagerComtroller {
    @Autowired
    private HrManagerService hrManagerService;

    private MIBEmployee findMIBEmployee, addMIBEmployee;
    private List<FreePersona> freePersonas;
    private FreePersona findFreePersona;
    List<Role> role;

    @ModelAttribute("role")
    public List<Role> getRole() {
        role = hrManagerService.getAllRole();
        return role;
    }

    @GetMapping({"/hrIndex"})
    public ModelAndView hrIndexGet(Model model) {
        findMIBEmployee = new MIBEmployee();
        findFreePersona = new FreePersona();
        addMIBEmployee = new MIBEmployee();

        model.addAttribute("findMIBEmployee", findMIBEmployee);
        model.addAttribute("findFreePersona", findFreePersona);
        model.addAttribute("addMIBEmployee", addMIBEmployee);

        return LoadForm(findMIBEmployee, findFreePersona, model);
    }

    @PostMapping({"/hrIndex"})
    public ModelAndView hrIndexPost(@ModelAttribute("findMIBEmployee") MIBEmployee findMIBEmployee, @ModelAttribute("findFreePersona") FreePersona findFreePersona, Model model) {
        return LoadForm(findMIBEmployee, findFreePersona, model);
    }

    @PostMapping({"/addMIBEmployee"})
    public ModelAndView addMIBEmployeePost(@ModelAttribute MIBEmployee addMIBEmployee, Model model) {
        hrManagerService.insertPassport(addMIBEmployee);
        return LoadForm(findMIBEmployee, findFreePersona, model);
    }

    @PostMapping({"/deleteMIBEmployee"})
    public ModelAndView deleteMIBEmployeePost(@ModelAttribute MIBEmployee addMIBEmployee, Model model) {
        hrManagerService.deletePassport(addMIBEmployee);
        return LoadForm(findMIBEmployee, findFreePersona, model);
    }

    @PostMapping({"/updateMIBEmployee"})
    public ModelAndView updateMIBEmployeePost(@ModelAttribute MIBEmployee addMIBEmployee, Model model) {
        hrManagerService.updatePassport(addMIBEmployee);
        return LoadForm(findMIBEmployee, findFreePersona, model);
    }

    private ModelAndView LoadForm(MIBEmployee findMIBEmployee, FreePersona findFreePersona, Model model) {
        this.findMIBEmployee = findMIBEmployee;
        this.findFreePersona = findFreePersona;

        model.addAttribute("findMIBEmployee", findMIBEmployee);
        model.addAttribute("findFreePersona", findFreePersona);

        return new ModelAndView("hr/index", model.asMap());
    }
}
