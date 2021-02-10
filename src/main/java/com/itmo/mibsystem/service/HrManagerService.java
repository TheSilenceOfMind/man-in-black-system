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

    @Autowired
    private UserService userService;

    public List<FreePersona> getAllFreePersonaByFilds(List<FreePersona> personas, FreePersona filds) {
        List<FreePersona> findFreePersonas = new ArrayList<FreePersona>();
        for(int i = 0; i < personas.size(); i++) {
            if(filds.getAge().length() != 0 && !personas.get(i).getAge().equals(filds.getAge())) {
                continue;
            }
            if(filds.getProfession().length() != 0 && !personas.get(i).getProfession().equals(filds.getProfession())) {
                continue;
            }
            if(filds.getEducation().length() != 0 && !personas.get(i).getEducation().equals(filds.getEducation())) {
                continue;
            }
            if(filds.getDescription().length() != 0 && !personas.get(i).getDescription().equals(filds.getDescription())) {
                continue;
            }
            findFreePersonas.add(personas.get(i));
        }
        return findFreePersonas;
    }

    public List<FreePersona> deleteFreePersonaById(List<FreePersona> personas, Long id) {
        for(int i = 0; i < personas.size(); i++) {
            if(personas.get(i).getFreePersonaId() == id) {
                personas.remove(i);
                break;
            }
        }
        return personas;
    }

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

    public List<Role> getRolebyUserId(Long userId) {
        List<User> user = new ArrayList<User>();
        userRepository.findUserByUserId(userId).iterator().forEachRemaining(user::add);

        return user.get(0).getRoles();
    }

    public User getUserbyId(Long userId) {
        List<User> user = new ArrayList<User>();
        userRepository.findUserByUserId(userId).iterator().forEachRemaining(user::add);

        return user.get(0);
    }

    public List<User> getAllUser() {
        List<User> user = new ArrayList<User>();
        userRepository.findAll().iterator().forEachRemaining(user::add);

        return user;
    }

    public List<MIBEmployee> getMIBEmployeeByFilds(String name, String age, Long idCurator, String discription) {
        return mIBEmployeeRepository.findMIBEmployeesByNameAndIdСuratorAndDescription(name, age, idCurator, discription);
    }

    public MIBEmployee insertMIBEmployee(MIBEmployee mIBEmployee, List<FreePersona> personas) {
        if(mIBEmployee.getUsername().length() == 0 || mIBEmployee.getPassword().length() == 0) {
            return null;
        }

        if(mIBEmployee.getIdFreePeople() != 0L) {
            deleteFreePersonaById(personas, mIBEmployee.getIdFreePeople());
        }

        User buffUser = userService.saveUser(new User(0L, mIBEmployee.getUsername(), mIBEmployee.getPassword(), false), mIBEmployee.getIdRole(), true);

        mIBEmployee.setIdUser(buffUser.getUserId());

        return mIBEmployeeRepository.save(mIBEmployee);
    }

    public void deleteMIBEmployee(MIBEmployee mIBEmployee) {
        mIBEmployeeRepository.deleteById(mIBEmployee.getMIBEmployeeId());
        userService.deleteUserById(mIBEmployee.getIdUser());
    }

    public MIBEmployee updateMIBEmployee(MIBEmployee mIBEmployee){
        User buff =  new User(mIBEmployee.getIdUser(), mIBEmployee.getUsername(), mIBEmployee.getPassword(), false);
        if(mIBEmployee.getPassword() == null || mIBEmployee.getPassword().length() == 0) {
            buff.setPassword(getUserbyId(buff.getUserId()).getPassword());
            userService.saveUser(buff, mIBEmployee.getIdRole(),false);
        }
        else {
            userService.saveUser(buff, mIBEmployee.getIdRole(),true);
        }
        return mIBEmployeeRepository.save(mIBEmployee);
    }
}
