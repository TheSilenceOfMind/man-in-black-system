package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.distribute.technology.DeliveryTypeRepository;
import com.itmo.mibsystem.model.distribute.technology.DeliveryType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DistributeTechnologyServiceTests {

    private static Long deliveryTypeId;

    @BeforeAll
    static void initIds() {
        deliveryTypeId = 130L;
    }

    @Autowired
    DistributeTechnologyService distributeTechnologyService;

    @Autowired
    DeliveryTypeRepository deliveryTypeRepository;

    @Test
    void getAllDeliveryType_shouldFind() {
        List<DeliveryType> given = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            given.add(generateDeliveryType());
        }

        deliveryTypeRepository.saveAll(given);
        List<DeliveryType> found = distributeTechnologyService.getAllDeliveryType();

        assertTrue(containsAllDeliveryTypes(given, found));
    }

    private static DeliveryType generateDeliveryType() {
        deliveryTypeId++;
        return new DeliveryType(deliveryTypeId, "type" + deliveryTypeId);
    }

    private boolean containsAllDeliveryTypes(List<DeliveryType> given, List<DeliveryType> found) {
        List<String> givenTypes = given.stream().map(DeliveryType::getType).collect(Collectors.toList());
        List<String> foundTypes = found.stream()
            .map(DeliveryType::getType)
            .filter( it -> givenTypes.contains(it) )
            .collect(Collectors.toList());
        return foundTypes.size() == givenTypes.size();
    }
}
