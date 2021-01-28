package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.distribute.technology.*;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
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

import java.util.ArrayList;
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
    private BuyTechnologyMarket findBuyTechnologyMarket;
    private BuyTechnologyDocument findBuyTechnologyDocument, addBuyTechnologyDocument;
    private DistributeTechnologyItem findDistributeTechnologyItem, addDistributeTechnologyItem;

    List<Technology> technologys;
    List<PaymentType> paymentType;
    List<TypeContract> typeContracts;
    List<AlienRace> alienRace;
    List<AlienPassport> aliens;
    List<DeliveryType> deliveryType;
    List<Role> role;
    List<MIBEmployee> mIBEmployee;
    List<BuyTechnologyMarket> buyTechnologyMarkets;

    @ModelAttribute("technologys")
    public List<Technology> getTechnology() {
        technologys = researcherService.getTechnologies();
        return technologys;
    }

    @ModelAttribute("paymentTypes")
    public List<PaymentType> getPaymentType() {
        paymentType = distributeTechnologyService.getAllPaymentType();
        return paymentType;
    }

    @ModelAttribute("alienRaces")
    public List<AlienRace> getAlienRace() {
        alienRace = passporterService.getAllRace();
        return alienRace;
    }

    @ModelAttribute("aliens")
    public List<AlienPassport> getAliens() {
        aliens = passporterService.getAliens();
        return aliens;
    }

    @ModelAttribute("deliveryTypes")
    public List<DeliveryType> getDeliveryType() {
        deliveryType = distributeTechnologyService.getAllDeliveryType();
        return deliveryType;
    }

    @ModelAttribute("typeContracts")
    public List<TypeContract> getTypeContracts() {
        typeContracts = distributeTechnologyService.getAllTypeContract();
        return typeContracts;
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

    @ModelAttribute("addSellTechnologyDocument")
    public SellTechnologyDocument getAddSellTechnologyDocument() {
        return addSellTechnologyDocument;
    }

    @ModelAttribute("addBuyTechnologyDocument")
    public BuyTechnologyDocument getAddBuyTechnologyDocument() {
        return addBuyTechnologyDocument;
    }

    @GetMapping({"/technologistIndex"})
    public ModelAndView technologistlogistIndexGet(Model model) {
        findSellTechnologyDocument = new SellTechnologyDocument();
        addSellTechnologyDocument = new SellTechnologyDocument();
        findBuyTechnologyMarket = new BuyTechnologyMarket();
        findBuyTechnologyDocument = new BuyTechnologyDocument();
        addBuyTechnologyDocument = new BuyTechnologyDocument();
        findDistributeTechnologyItem = new DistributeTechnologyItem();
        buyTechnologyMarkets = new ArrayList<BuyTechnologyMarket>();

        buyTechnologyMarkets.add(new BuyTechnologyMarket(0L, passporterService, distributeTechnologyService));
        buyTechnologyMarkets.add(new BuyTechnologyMarket(1L, passporterService, distributeTechnologyService));
        buyTechnologyMarkets.add(new BuyTechnologyMarket(2L, passporterService, distributeTechnologyService));

        model.addAttribute("findSellTechnologyDocument", findSellTechnologyDocument);
        model.addAttribute("addSellTechnologyDocument", addSellTechnologyDocument);
        model.addAttribute("findBuyTechnologyMarket", findBuyTechnologyMarket);
        model.addAttribute("findBuyTechnologyDocument", findBuyTechnologyDocument);
        model.addAttribute("addBuyTechnologyDocument", addBuyTechnologyDocument);
        model.addAttribute("findDistributeTechnologyItem", findDistributeTechnologyItem);

        return LoadForm(findSellTechnologyDocument, findBuyTechnologyMarket, findBuyTechnologyDocument, findDistributeTechnologyItem,  model);
    }

    @PostMapping({"/technologistIndex"})
    public ModelAndView technologistIndexPost(
            @ModelAttribute("findSellTechnologyDocument") SellTechnologyDocument findSellTechnologyDocument,
            @ModelAttribute("findBuyTechnologyMarket") BuyTechnologyMarket findBuyTechnologyMarket,
            @ModelAttribute("findBuyTechnologyDocument") BuyTechnologyDocument findBuyTechnologyDocument,
            @ModelAttribute("findDistributeTechnologyItem") DistributeTechnologyItem findDistributeTechnologyItem,
            Model model) {
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyMarket, findBuyTechnologyDocument, findDistributeTechnologyItem, model);
    }

    @PostMapping({"/addSellTechnologyDocument"})
    public ModelAndView addSellTechnologyDocumentPost(@ModelAttribute SellTechnologyDocument addSellTechnologyDocument, Model model) {
        distributeTechnologyService.insertSellTechnologyDocument(addSellTechnologyDocument);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyMarket, findBuyTechnologyDocument, findDistributeTechnologyItem, model);
    }

    @PostMapping({"/deleteSellTechnologyDocument"})
    public ModelAndView deleteSellTechnologyDocumentPost(@ModelAttribute SellTechnologyDocument addSellTechnologyDocument, Model model) {
        distributeTechnologyService.deleteSellTechnologyDocument(addSellTechnologyDocument);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyMarket, findBuyTechnologyDocument, findDistributeTechnologyItem, model);
    }

    @PostMapping({"/updateSellTechnologyDocument"})
    public ModelAndView updateSellTechnologyDocumentPost(@ModelAttribute SellTechnologyDocument addSellTechnologyDocument, Model model) {
        distributeTechnologyService.updateSellTechnologyDocument(addSellTechnologyDocument);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyMarket, findBuyTechnologyDocument, findDistributeTechnologyItem, model);
    }

    @PostMapping({"/addDistributeTechnologyItem"})
    public ModelAndView addDistributeTechnologyItemPost(@ModelAttribute DistributeTechnologyItem addDistributeTechnologyItem, Model model) {
        distributeTechnologyService.insertDistributeTechnologyItem(addDistributeTechnologyItem);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyMarket, findBuyTechnologyDocument, findDistributeTechnologyItem, model);
    }

    @PostMapping({"/deleteDistributeTechnologyItem"})
    public ModelAndView deleteDistributeTechnologyItemPost(@ModelAttribute DistributeTechnologyItem addDistributeTechnologyItem, Model model) {
        distributeTechnologyService.deleteDistributeTechnologyItem(addDistributeTechnologyItem);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyMarket, findBuyTechnologyDocument, findDistributeTechnologyItem, model);
    }

    @PostMapping({"/updateDistributeTechnologyItem"})
    public ModelAndView updateDistributeTechnologyItemPost(@ModelAttribute DistributeTechnologyItem addDistributeTechnologyItem, Model model) {
        distributeTechnologyService.updateDistributeTechnologyItem(addDistributeTechnologyItem);
        return LoadForm(findSellTechnologyDocument, findBuyTechnologyMarket, findBuyTechnologyDocument, findDistributeTechnologyItem, model);
    }

    private ModelAndView LoadForm(SellTechnologyDocument findSellTechnologyDocument, BuyTechnologyMarket findBuyTechnologyMarket, BuyTechnologyDocument findBuyTechnologyDocument, DistributeTechnologyItem findDistributeTechnologyItem, Model model) {
        List<BuyTechnologyMarket> findBuyTechnologyMarkets = new ArrayList<BuyTechnologyMarket>();
        this.findSellTechnologyDocument = findSellTechnologyDocument;
        this.findBuyTechnologyMarket = findBuyTechnologyMarket;
        this.findDistributeTechnologyItem = findDistributeTechnologyItem;

        model.addAttribute("findSellTechnologyDocument", findSellTechnologyDocument);
        model.addAttribute("findBuyTechnologyMarket", findBuyTechnologyMarket);
        model.addAttribute("findDistributeTechnologyItem", findDistributeTechnologyItem);

        List<SellTechnologyDocument> sellTechnologyDocuments = distributeTechnologyService.getSellTechnologyDocumentByFilds(findSellTechnologyDocument.getCostForOne(),findSellTechnologyDocument.getCount(),findSellTechnologyDocument.getIdTechnology(),findSellTechnologyDocument.getIdTypeContract(),findSellTechnologyDocument.getIdAlien(),findSellTechnologyDocument.getDescription());
        for(int i = 0; i < sellTechnologyDocuments.size(); i++) {
            for(int j = 0; j < technologys.size(); j++) {
                if(sellTechnologyDocuments.get(i).getIdTechnology() == technologys.get(j).getTechnologyId()) {
                    sellTechnologyDocuments.get(i).setTechnologyName(technologys.get(j).getName());
                    break;
                }
            }

            for(int j = 0; j < aliens.size(); j++) {
                if(sellTechnologyDocuments.get(i).getIdAlien() == aliens.get(j).getPassportId()) {
                    sellTechnologyDocuments.get(i).setAlienName(aliens.get(j).getName());
                    break;
                }
            }

            for(int j = 0; j < typeContracts.size(); j++) {
                if(sellTechnologyDocuments.get(i).getIdTypeContract() == typeContracts.get(j).getTypeContractId()) {
                    sellTechnologyDocuments.get(i).setTypeContractName(typeContracts.get(j).getType());
                    break;
                }
            }
        }
        model.addAttribute("sellTechnologyDocuments", sellTechnologyDocuments);

        findBuyTechnologyMarkets = distributeTechnologyService.getBuyTechnologyDocumentByFilds(findBuyTechnologyMarket, buyTechnologyMarkets);
        for(int i = 0; i < findBuyTechnologyMarkets.size(); i++) {
            for(int j = 0; j < alienRace.size(); j++) {
                if(findBuyTechnologyMarkets.get(i).getIdRace() == alienRace.get(j).getRaceId()) {
                    findBuyTechnologyMarkets.get(i).setRaceName(alienRace.get(j).getName());
                    break;
                }
            }
            for(int j = 0; j < deliveryType.size(); j++) {
                if(findBuyTechnologyMarkets.get(i).getIdDeliveryType() == deliveryType.get(j).getDeliveryTypeId()) {
                    findBuyTechnologyMarkets.get(i).setDeliveryTypeName(deliveryType.get(j).getType());
                    break;
                }
            }
        }
        model.addAttribute("buyTechnologyMarkets", findBuyTechnologyMarkets);

        return new ModelAndView("technologist/index", model.asMap());
    }
}
