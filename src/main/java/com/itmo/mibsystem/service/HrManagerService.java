package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.hrmanager.MIBEmployeeRepository;
import com.itmo.mibsystem.dao.hrmanager.RoleRepository;
import com.itmo.mibsystem.dao.hrmanager.UserRepository;
import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.model.hrmanager.FreePersona;
import com.itmo.mibsystem.model.hrmanager.MIBEmployee;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import com.itmo.mibsystem.model.passporter.AlienRace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HrManagerService {
    @Autowired
    private MIBEmployeeRepository mIBEmployeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<MIBEmployee> getAllMIBEmployee() {
        List<MIBEmployee> mIBEmployee = new ArrayList<MIBEmployee>();
        mIBEmployeeRepository.findAll().iterator().forEachRemaining(mIBEmployee::add);

        return mIBEmployee;
    }

    public List<Role> getAllRole() {
        List<Role> role = new ArrayList<Role>();
        roleRepository.findAll().iterator().forEachRemaining(role::add);

        return role;
    }

    public List<User> getAllUser() {
        List<User> user = new ArrayList<User>();
        userRepository.findAll().iterator().forEachRemaining(user::add);

        return user;
    }

    public List<FreePersona> getAllFreePersona() {
        List<FreePersona> freePersona = new ArrayList<FreePersona>();

        return freePersona;
    }

    public List<MIBEmployee> getMIBEmployeeByFilds(String name, String age, long idUser, long idRole, long idСurator, String discription) {
        return mIBEmployeeRepository.findMIBEmployeesByNameAndAgeAndIdUserAndIdRoleAndIdCuratorAndDescription(name , age, idUser, idRole, idСurator, discription);
    }

    public void insertPassport(MIBEmployee mIBEmployee) {
        mIBEmployeeRepository.save(mIBEmployee);
    }

    public void deletePassport(MIBEmployee mIBEmployee) {
        mIBEmployeeRepository.deleteById(mIBEmployee.getMIBEmployeeId());
    }

    public void updatePassport(MIBEmployee mIBEmployee){
        mIBEmployeeRepository.save(mIBEmployee);
    }
}
