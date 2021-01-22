package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.distribute.technology.*;
import com.itmo.mibsystem.model.distribute.technology.*;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributeTechnologyService {
    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private TypeContractRepository typeContractRepository;

    @Autowired
    private SellTechnologyDocumentRepository sellTechnologyDocumentRepository;

    @Autowired
    private DistributeTechnologyItemRepository distributeTechnologyItemRepository;

    public List<DeliveryType> getAllDeliveryType() {
        List<DeliveryType> deliveryType = new ArrayList<DeliveryType>();
        deliveryTypeRepository.findAll().iterator().forEachRemaining(deliveryType::add);

        return deliveryType;
    }

    public List<PaymentType> getAllPaymentType() {
        List<PaymentType> paymentType = new ArrayList<PaymentType>();
        paymentTypeRepository.findAll().iterator().forEachRemaining(paymentType::add);

        return paymentType;
    }

    public List<TypeContract> getAllTypeContract() {
        List<TypeContract> typeContract = new ArrayList<TypeContract>();
        typeContractRepository.findAll().iterator().forEachRemaining(typeContract::add);

        return typeContract;
    }

    public List<SellTechnologyDocument> getAllSellTechnologyDocument() {
        List<SellTechnologyDocument> sellTechnologyDocument = new ArrayList<SellTechnologyDocument>();
        sellTechnologyDocumentRepository.findAll().iterator().forEachRemaining(sellTechnologyDocument::add);

        return sellTechnologyDocument;
    }

    public List<DistributeTechnologyItem> getAllDistributeTechnologyItem() {
        List<DistributeTechnologyItem> distributeTechnologyItem = new ArrayList<DistributeTechnologyItem>();
        distributeTechnologyItemRepository.findAll().iterator().forEachRemaining(distributeTechnologyItem::add);

        return distributeTechnologyItem;
    }

    public List<SellTechnologyDocument> getSellTechnologyDocumentByFilds(String costForOne, Long count, long idTechnology, long idTypeContract, long idAlien, String discription) {
        return sellTechnologyDocumentRepository.findSellTechnologyDocumentsByCostForOneAAndCountAndIdTechnologyAndIdTypeContractAndIdAlienAndDescription(costForOne , count, idTechnology, idTypeContract, idAlien,  discription);
    }

    public List<DistributeTechnologyItem> getDistributeTechnologyItemByFilds(Long count, long idTechnology, long idAgent, String discription) {
        return distributeTechnologyItemRepository.findDistributeTechnologyItemsByIdTechnologyAndIdAgentAnd(count , idTechnology, idAgent, discription);
    }

    public void insertSellTechnologyDocument(SellTechnologyDocument sellTechnologyDocument) {
        sellTechnologyDocumentRepository.save(sellTechnologyDocument);
    }

    public void deleteSellTechnologyDocument(SellTechnologyDocument sellTechnologyDocument) {
        sellTechnologyDocumentRepository.deleteById(sellTechnologyDocument.getSellTechnologyDocumentId());
    }

    public void updateSellTechnologyDocument(SellTechnologyDocument sellTechnologyDocument){
        sellTechnologyDocumentRepository.save(sellTechnologyDocument);
    }

    public void insertDistributeTechnologyItem(DistributeTechnologyItem distributeTechnologyItem) {
        distributeTechnologyItemRepository.save(distributeTechnologyItem);
    }

    public void deleteDistributeTechnologyItem(DistributeTechnologyItem distributeTechnologyItem) {
        distributeTechnologyItemRepository.deleteById(distributeTechnologyItem.getDistributeTechnologyItemId());
    }

    public void updateDistributeTechnologyItem(DistributeTechnologyItem distributeTechnologyItem){
        distributeTechnologyItemRepository.save(distributeTechnologyItem);
    }

}
