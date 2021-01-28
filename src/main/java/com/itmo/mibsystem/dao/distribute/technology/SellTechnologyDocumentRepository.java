package com.itmo.mibsystem.dao.distribute.technology;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.distribute.technology.SellTechnologyDocument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SellTechnologyDocumentRepository extends CrudRepository<SellTechnologyDocument, Long> {
    Optional<SellTechnologyDocument> findAllByDescription(String description);

/*
    @Query("SELECT c FROM MIBEmployee c " +
            "WHERE (:name is null or :name = '' or c.name = :name) and " +
            "(:idCurator is null or :idCurator = 0L or c.idCurator = :idCurator) and" +
            "(:description is null or :description = '' or c.description = :description) order by " +
            "c.MIBEmployeeId ASC")
*/
    @Query("SELECT c FROM SellTechnologyDocument c " +
            "WHERE (:costForOne is null or :costForOne = '' or c.costForOne = :costForOne) and " +
            "(:count is null or :count = 0L or c.count = :count) and " +
            "(:idTechnology is null or :idTechnology = 0L or c.idTechnology = :idTechnology) and " +
            "(:idTypeContract is null or :idTypeContract = 0L or c.idTypeContract = :idTypeContract) and " +
            "(:idAlien is null or :idAlien = 0L or c.idAlien = :idAlien) and " +
            "(:description is null or :description = '' or c.description = :description) order by " +
            "c.sellTechnologyDocumentId ASC")
    List<SellTechnologyDocument> findSellTechnologyDocumentsByCostForOneAAndCountAndIdTechnologyAndIdTypeContractAndIdAlienAndDescription(
            @Param("costForOne") String costForOne,
            @Param("count") long count, @Param("idTechnology") long idTechnology,
            @Param("idTypeContract") long idTypeContract, @Param("idAlien") long idAlien,
            @Param("description") String description);
}