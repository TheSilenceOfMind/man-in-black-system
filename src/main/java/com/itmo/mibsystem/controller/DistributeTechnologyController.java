package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.distribute.technology.*;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import com.itmo.mibsystem.model.lawyer.EarthDocument;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import com.itmo.mibsystem.model.researcher.Technology;
import com.itmo.mibsystem.service.DistributeTechnologyService;
import com.itmo.mibsystem.service.HrManagerService;
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
public class DistributeTechnologyController {
    @Autowired
    private DistributeTechnologyService distributeTechnologyService;

    @Autowired
    private HrManagerService hrManagerService;

    @Autowired
    private ResearcherService researcherService;

    @Autowired
    private PassporterService passporterService;

    private SellTechnologyDocument findSellTechnologyDocument, addSellTechnologyDocument;
    private BuyTechnologyDocument findBuyTechnologyDocument;
    private DistributeTechnologyItem findDistributeTechnologyItem, addDistributeTechnologyItem;

    List<Technology> technology;
    List<PaymentType> paymentType;
    List<AlienRace> alienRace;
    List<DeliveryType> deliveryType;
    List<Role> role;
    List<MIBEmployee> mIBEmployee;

    @ModelAttribute("technology")
    public List<Technology> getTechnology() {
        technology = researcherService.getTechnologies();
        return technology;
    }

    @ModelAttribute("paymentType")
    public List<PaymentType> getPaymentType() {
        paymentType = distributeTechnologyService.getAllPaymentType();
        return paymentType;
    }

    @ModelAttribute("alienRace")
    public List<AlienRace> getAlienRace() {
        alienRace = passporterService.getAllRace();
        return alienRace;
    }

    @ModelAttribute("deliveryType")
    public List<DeliveryType> getDeliveryType() {
        deliveryType = distributeTechnologyService.getAllDeliveryType();
        return deliveryType;
    }

    @ModelAttribute("role")
    public List<Role> getRole() {
        role = hrManagerService.getAllRole();
        return role;
    }

    @ModelAttribute("mIBEmployee")
    public List<MIBEmployee> getMIBEmployee() {
        mIBEmployee = hrManagerService.getAllMIBEmployee();
        return mIBEmployee;
    }

    @GetMapping({"/technologistIndex"})
    public ModelAndView technologistlogistIndexGet(Model model) {
        findSellTechnologyDocument = new SellTechnologyDocument();
        addSellTechnologyDocument = new SellTechnologyDocument();
        findBuyTechnologyDocument = new BuyTechnologyDocument();
        findDistributeTechnologyItem = new DistributeTechnologyItem();

        model.addAttribute("findSellTechnologyDocument", findSellTechnologyDocument);
        model.addAttribute("addSellTechnologyDocument", addSellTechnologyDocument);
        model.addAttribute("findBuyTechnologyDocument", findBuyTechnologyDocument);
        model.addAttribute("findDistributeTechnologyItem", findDistributeTechnologyItem);

        return LoadForm(findSellTechnologyDocument, findBuyTechnologyDocument, findDistributeTechnologyItem,  model);
    }

    @PostMapping({"/technologistIndex"})
    public ModelAndView technologistIndexPost(@ModelAttribute("findFildsPassport") AlienPassport findFildsPassport, @ModelAttribute("findFildsLawyer") EarthDocument findFildsLawyer, Model model) {
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyDocument, findDistributeTechnologyItem,  model);
    }

    @PostMapping({"/addSellTechnologyDocument"})
    public ModelAndView addSellTechnologyDocumentPost(@ModelAttribute SellTechnologyDocument addSellTechnologyDocument, Model model) {
        distributeTechnologyService.insertSellTechnologyDocument(addSellTechnologyDocument);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyDocument, findDistributeTechnologyItem,  model);
    }

    @PostMapping({"/deleteSellTechnologyDocument"})
    public ModelAndView deleteSellTechnologyDocumentPost(@ModelAttribute SellTechnologyDocument addSellTechnologyDocument, Model model) {
        distributeTechnologyService.deleteSellTechnologyDocument(addSellTechnologyDocument);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyDocument, findDistributeTechnologyItem,  model);
    }

    @PostMapping({"/updateSellTechnologyDocument"})
    public ModelAndView updateSellTechnologyDocumentPost(@ModelAttribute SellTechnologyDocument addSellTechnologyDocument, Model model) {
        distributeTechnologyService.updateSellTechnologyDocument(addSellTechnologyDocument);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyDocument, findDistributeTechnologyItem,  model);
    }

    @PostMapping({"/addDistributeTechnologyItem"})
    public ModelAndView addDistributeTechnologyItemPost(@ModelAttribute DistributeTechnologyItem addDistributeTechnologyItem, Model model) {
        distributeTechnologyService.insertDistributeTechnologyItem(addDistributeTechnologyItem);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyDocument, findDistributeTechnologyItem,  model);
    }

    @PostMapping({"/deleteDistributeTechnologyItem"})
    public ModelAndView deleteDistributeTechnologyItemPost(@ModelAttribute DistributeTechnologyItem addDistributeTechnologyItem, Model model) {
        distributeTechnologyService.deleteDistributeTechnologyItem(addDistributeTechnologyItem);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyDocument, findDistributeTechnologyItem,  model);
    }

    @PostMapping({"/updateDistributeTechnologyItem"})
    public ModelAndView updateDistributeTechnologyItemPost(@ModelAttribute DistributeTechnologyItem addDistributeTechnologyItem, Model model) {
        distributeTechnologyService.updateDistributeTechnologyItem(addDistributeTechnologyItem);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyDocument, findDistributeTechnologyItem,  model);
    }

    private ModelAndView LoadForm(SellTechnologyDocument findSellTechnologyDocument, BuyTechnologyDocument findBuyTechnologyDocument, DistributeTechnologyItem findDistributeTechnologyItem, Model model) {
        this.findSellTechnologyDocument = findSellTechnologyDocument;
        this.findBuyTechnologyDocument = findBuyTechnologyDocument;
        this.findDistributeTechnologyItem = findDistributeTechnologyItem;

        return new ModelAndView("technologist/index", model.asMap());
    }
}
