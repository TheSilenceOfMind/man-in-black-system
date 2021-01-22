package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.SellTechnologyDocument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SellTechnologyDocumentRepository extends CrudRepository<SellTechnologyDocument, Long> {
    Optional<SellTechnologyDocument> findAllByDescription(String description);

    @Query("SELECT c FROM SellTechnologyDocument c")
    List<SellTechnologyDocument> findSellTechnologyDocumentsByCostForOneAAndCountAndIdTechnologyAndIdTypeContractAndIdAlienAndDescription(String cost_for_one, long count, long idTechnology, long idTypeContract, long idAlien, String description);
}