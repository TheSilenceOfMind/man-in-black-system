package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.hrmanager.FreePersona;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import com.itmo.mibsystem.model.lawyer.EarthDocument;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.service.HrManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class HrManagerComtroller {
    @Autowired
    private HrManagerService hrManagerService;

    private MIBEmployee findMIBEmployee, addMIBEmployee;
    private List<FreePersona> freePersonas;
    private FreePersona findFreePersona;
    List<Role> role;
    List<MIBEmployee> peoples;
    @ModelAttribute("roles")
    public List<Role> getRole() {
        role = hrManagerService.getAllRole();
        return role;
    }

    @ModelAttribute("peoples")
    public List<MIBEmployee> getPeoples() {
        peoples = hrManagerService.getAllMIBEmployee();
        return peoples;
    }

    @ModelAttribute("addMIBEmployee")
    public MIBEmployee getAddMIBEmployee() {
        return addMIBEmployee;
    }

    @GetMapping({"/hrIndex"})
    public ModelAndView hrIndexGet(Model model) {
        findMIBEmployee = new MIBEmployee();
        findFreePersona = new FreePersona();
        addMIBEmployee = new MIBEmployee();
        freePersonas = new ArrayList<FreePersona>();

        freePersonas.add(new FreePersona(1L));
        freePersonas.add(new FreePersona(2L));
        freePersonas.add(new FreePersona(3L));

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
        hrManagerService.insertMIBEmployee(addMIBEmployee, freePersonas);
        return LoadForm(findMIBEmployee, findFreePersona, model);
    }

    @PostMapping({"/deleteMIBEmployee"})
    public ModelAndView deleteMIBEmployeePost(@ModelAttribute MIBEmployee addMIBEmployee, Model model) {
        hrManagerService.deleteMIBEmployee(addMIBEmployee);
        return LoadForm(findMIBEmployee, findFreePersona, model);
    }

    @PostMapping({"/updateMIBEmployee"})
    public ModelAndView updateMIBEmployeePost(@ModelAttribute MIBEmployee addMIBEmployee, Model model) {
        hrManagerService.updateMIBEmployee(addMIBEmployee);
        return LoadForm(findMIBEmployee, findFreePersona, model);
    }

    private ModelAndView LoadForm(MIBEmployee findMIBEmployee, FreePersona findFreePersona, Model model) {
        List<FreePersona> findFreePersonas = new ArrayList<FreePersona>();
        Long idCurator = 0L;
        this.findMIBEmployee = findMIBEmployee;
        this.findFreePersona = findFreePersona;

        model.addAttribute("findMIBEmployee", findMIBEmployee);
        model.addAttribute("findFreePersona", findFreePersona);

        List<MIBEmployee> MIBEmployees = hrManagerService.getMIBEmployeeByFilds(findMIBEmployee.getName(), findMIBEmployee.getAge(), findMIBEmployee.getIdCurator(), findMIBEmployee.getDescription());
        for(int i = 0; i < MIBEmployees.size(); i ++) {
            for(int j = 0; j < peoples.size(); j ++) {
                if(MIBEmployees.get(i).getIdCurator() == peoples.get(j).getMIBEmployeeId()) {
                    MIBEmployees.get(i).setCuratorName(peoples.get(j).getName());
                    break;
                }
            }

            if(MIBEmployees.get(i).getCuratorName() == null || MIBEmployees.get(i).getCuratorName().length() == 0) {
                MIBEmployees.get(i).setCuratorName("None");
            }

            Role r = hrManagerService.getRolebyUserId(MIBEmployees.get(i).getIdUser()).get(0);
            MIBEmployees.get(i).setIdRole((long) r.getRoleId());
            MIBEmployees.get(i).setRoleName(r.getRoleName());

            MIBEmployees.get(i).setUsername(hrManagerService.getUserbyId(MIBEmployees.get(i).getIdUser()).getUsername());
        }

        model.addAttribute("MIBEmployees", MIBEmployees);

        findFreePersonas = hrManagerService.getAllFreePersonaByFilds(freePersonas, findFreePersona);
        model.addAttribute("freePersonas", findFreePersonas);

        return new ModelAndView("hr/index", model.asMap());
    }
}
