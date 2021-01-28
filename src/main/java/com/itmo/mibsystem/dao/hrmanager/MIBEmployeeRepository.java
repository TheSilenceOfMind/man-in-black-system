package com.itmo.mibsystem.dao.hrmanager;

import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MIBEmployeeRepository extends CrudRepository<MIBEmployee, Long> {
    Optional<MIBEmployee> findAllByName(String name);

    @Query("SELECT c FROM MIBEmployee c " +
            "WHERE (:name is null or :name = '' or c.name = :name) and " +
            "(:idCurator is null or :idCurator = 0L or c.idCurator = :idCurator) and" +
            "(:description is null or :description = '' or c.description = :description) order by " +
            "c.MIBEmployeeId ASC")
    List<MIBEmployee> findMIBEmployeesByNameAndIdСuratorAndDescription(@Param("name") String name, @Param("idCurator") Long idCurator, @Param("description") String description);
}