package com.itmo.mibsystem.dao.lawyer;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.lawyer.EarthDocument;
import com.itmo.mibsystem.model.op_agent.ActDetention;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EarthDocumentRepository extends CrudRepository<EarthDocument, Long> {
    Optional<EarthDocument> findAllByEarthName(String earthName);

    @Query("SELECT c FROM EarthDocument c " +
            "WHERE (:earthName is null or :earthName = '' or c.earthName = :earthName) and " +
            "(:idNation is null or :idNation = 0L or c.idNation = :idNation) and " +
            "(:idTypeDocument is null or :idTypeDocument = 0L or c.idTypeDocument = :idTypeDocument) and " +
            "(:idAlien is null or :idAlien = 0L or c.idAlien = :idAlien) and " +
            "(:description is null or :description = '' or c.description = :description) order by " +
            "c.earthDocumentId ASC")
    List<EarthDocument> findEarthDocumentByEarthNameAndIdNationAndIdTypeDocumentAndidAlienAndDescription(@Param("earthName") String earthName, @Param("idNation") Long idNation, @Param("idTypeDocument") Long idTypeDocument, @Param("idAlien") Long idAlien, @Param("description") String description);

}