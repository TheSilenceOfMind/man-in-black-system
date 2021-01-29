package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.distribute.technology.BuyTechnologyDocument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BuyTechnologyDocumentRepository extends CrudRepository<BuyTechnologyDocument, Long> {
    Optional<BuyTechnologyDocument> findAllByBuyTechnologyDocumentId(Long id);

    @Query("SELECT c FROM BuyTechnologyDocument c " +
            "WHERE (:count is null or :count = 0L or c.count = :count) and " +
            "(:idTechnology is null or :idTechnology = 0L or c.idTechnology = :idTechnology) and " +
            "(:idPaymentType is null or :idPaymentType = 0L or c.idPaymentType = :idPaymentType) and " +
            "(:idDeliveryType is null or :idDeliveryType = 0L or c.idDeliveryType = :idDeliveryType) and " +
            "(:description is null or :description = '' or c.description = :description) order by " +
            "c.buyTechnologyDocumentId ASC")
    List<BuyTechnologyDocument> findAllByFilds(@Param("count") Long count, @Param("idTechnology") Long idTechnology, @Param("idPaymentType") Long idPaymentType, @Param("idDeliveryType") Long idDeliveryType, @Param("description") String description);
}