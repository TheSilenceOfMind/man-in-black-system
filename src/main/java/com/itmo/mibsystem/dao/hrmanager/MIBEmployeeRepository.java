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

    @Query("SELECT c FROM MIBEmployee c ")
    List<MIBEmployee> findMIBEmployeesByNameAndAgeAndIdUserAndIdRoleAndIdCuratorAndDescription(@Param("name") String name, @Param("age") String age, @Param("idUser") Long idUser, @Param("idRole") Long idRole, @Param("idСurator") Long idСurator, @Param("description") String description);
}