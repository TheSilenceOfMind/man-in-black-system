package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.distribute.technology.*;
import com.itmo.mibsystem.model.distribute.technology.*;
import com.itmo.mibsystem.model.hrmanager.FreePersona;
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
        List<DeliveryType> deliveryType = new ArrayList<>();
        deliveryTypeRepository.findAll().iterator().forEachRemaining(deliveryType::add);

        return deliveryType;
    }

    public List<PaymentType> getAllPaymentType() {
        List<PaymentType> paymentType = new ArrayList<>();
        paymentTypeRepository.findAll().iterator().forEachRemaining(paymentType::add);

        return paymentType;
    }

    public List<TypeContract> getAllTypeContract() {
        List<TypeContract> typeContract = new ArrayList<>();
        typeContractRepository.findAll().iterator().forEachRemaining(typeContract::add);

        return typeContract;
    }

    public List<SellTechnologyDocument> getSellTechnologyDocumentByFilds(String costForOne, Long count, long idTechnology, long idTypeContract, long idAlien, String discription) {
        return sellTechnologyDocumentRepository.findSellTechnologyDocumentsByCostForOneAAndCountAndIdTechnologyAndIdTypeContractAndIdAlienAndDescription(costForOne , count, idTechnology, idTypeContract, idAlien,  discription);
    }

    public List<DistributeTechnologyItem> getDistributeTechnologyItemByFilds(Long count, String use, long idTechnology, long idAgent, String discription) {
        return distributeTechnologyItemRepository.findDistributeTechnologyItemsByIdTechnologyAndIdAgentAnd(count, use, idTechnology, idAgent, discription);
    }

    public List<BuyTechnologyMarket> getBuyTechnologyMarketByFilds(BuyTechnologyMarket findBuyTechnologyMarket, List<BuyTechnologyMarket> buyTechnologyMarkets) {
        List<BuyTechnologyMarket> findBuyTechnologyMarkets = new ArrayList<BuyTechnologyMarket>();
        for(int i = 0; i < buyTechnologyMarkets.size(); i++) {
            if(findBuyTechnologyMarket.getUse().length() != 0 && !buyTechnologyMarkets.get(i).getUse().equals(findBuyTechnologyMarket.getUse())) {
                continue;
            }
            if(findBuyTechnologyMarket.getIdRace() != 0L && buyTechnologyMarkets.get(i).getIdRace() != findBuyTechnologyMarket.getIdRace()) {
                continue;
            }
            if(findBuyTechnologyMarket.getIdDeliveryType() != 0L && buyTechnologyMarkets.get(i).getIdDeliveryType() != findBuyTechnologyMarket.getIdDeliveryType()) {
                continue;
            }
            if(findBuyTechnologyMarket.getDescription().length() != 0 && !buyTechnologyMarkets.get(i).getDescription().equals(findBuyTechnologyMarket.getDescription())) {
                continue;
            }
            findBuyTechnologyMarkets.add(buyTechnologyMarkets.get(i));
        }
        return findBuyTechnologyMarkets;
    }

    public List<BuyTechnologyMarket> deleteBuyTechnologyMarketById(List<BuyTechnologyMarket> buyTechnologyMarkets, Long id) {
        for(int i = 0; i < buyTechnologyMarkets.size(); i++) {
            if(buyTechnologyMarkets.get(i).getBuyTechnologyMarketId() == id) {
                buyTechnologyMarkets.remove(i);
                break;
            }
        }
        return buyTechnologyMarkets;
    }

    public List<BuyTechnologyDocument> getBuyTechnologyDocumentsByFilds(Long count, Long idTechnology, Long idPaymentType, Long idDeliveryType, String discription) {
        return buyTechnologyDocumentRepository.findAllByFilds(count, idTechnology, idPaymentType, idDeliveryType, discription);
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
