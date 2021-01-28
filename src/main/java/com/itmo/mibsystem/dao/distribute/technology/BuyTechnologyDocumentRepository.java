package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.distribute.technology.BuyTechnologyDocument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BuyTechnologyDocumentRepository extends CrudRepository<BuyTechnologyDocument, Long> {
    Optional<BuyTechnologyDocument> findAllByBuyTechnologyDocumentId(Long id);

    @Query("SELECT c FROM BuyTechnologyDocument c")
    List<BuyTechnologyDocument> findAllByFilds(Long count, Long idPaymentType, Long idDeliveryType, String description);
}