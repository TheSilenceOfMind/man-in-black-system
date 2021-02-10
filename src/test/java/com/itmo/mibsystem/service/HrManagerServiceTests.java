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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HrManagerServiceTests {
    @Autowired
    private HrManagerService hrManagerService;

    @Autowired
    private MIBEmployeeRepository mIBEmployeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void checkInsertMIBEmployee() {
        //Create test data
        Role role = CreateTestRole("testRole1");

        //Test
        MIBEmployee employee = new MIBEmployee("testName1", "10 age", "testDescription", "testUsername1", "testPassword", (long)role.getRoleId(), null);
        employee = hrManagerService.insertMIBEmployee(employee, new ArrayList<FreePersona>());

        MIBEmployee findEmployee = mIBEmployeeRepository.findById(employee.getMIBEmployeeId()).orElse(null);

        assertTrue(employee.equals(findEmployee));

        //Delete test data
        mIBEmployeeRepository.deleteById(employee.getMIBEmployeeId());
        userService.deleteUserById(userRepository.findByUsername("testUsername1").get().getUserId());
        roleRepository.deleteByRoleId(role.getRoleId());
    }

    @Test
    void checkDeleteMIBEmployee() {
        //Create test data
        Role role = CreateTestRole("testRole1");
        MIBEmployee employee = CreateTestMIBEmployee("testName1", "10 age", "testDescription", "testUsername1", "testPassword", (long)role.getRoleId());

        //Test
        hrManagerService.deleteMIBEmployee(employee);

        employee = mIBEmployeeRepository.findAllByName("testName1").orElse(null);
        assertNull(employee);

        //Delete test data
        roleRepository.deleteByRoleId(role.getRoleId());
    }

    @Test
    void checkUpdateMIBEmployee() {
        //Create test data
        Role role1 = CreateTestRole("testRole1");
        Role role2 = CreateTestRole("testRole2");
        MIBEmployee employee = CreateTestMIBEmployee("testName1", "10 age", "testDescription1", "testUsername1", "testPassword", (long)role1.getRoleId());

        //Test
        employee.setName("testName2");
        employee.setAge("10 age");
        employee.setDescription("testDescription2");
        MIBEmployee updateEmployee = hrManagerService.updateMIBEmployee(employee);

        assertTrue(updateEmployee.equals(employee));

        //Delete test data
        mIBEmployeeRepository.deleteById(employee.getMIBEmployeeId());
        userService.deleteUserById(userRepository.findByUsername("testUsername1").get().getUserId());
        roleRepository.deleteByRoleId(role1.getRoleId());
        roleRepository.deleteByRoleId(role2.getRoleId());
    }

    @Test
    void checkGetMIBEmployeeByFildsAll() {
        //Create test data
        Role role1 = CreateTestRole("testRole1");
        Role role2 = CreateTestRole("testRole2");
        List<MIBEmployee> newEmployee = new ArrayList<MIBEmployee>();
        newEmployee.add(CreateTestMIBEmployee("testName1", "10 age", "testDescription1", "testUsername1", "testPassword", (long)role1.getRoleId()));
        newEmployee.add(CreateTestMIBEmployee("testName2", "11 age", "testDescription1", "testUsername2", "testPassword", (long)role1.getRoleId()));
        newEmployee.add(CreateTestMIBEmployee("testName3", "10 age", "testDescription2", "testUsername3", "testPassword", (long)role1.getRoleId()));
        newEmployee.add(CreateTestMIBEmployee("testName4", "10 age", "testDescription1", "testUsername4", "testPassword", (long)role2.getRoleId()));

        //Test
        List<MIBEmployee> employees = hrManagerService.getMIBEmployeeByFilds("", "", 0L, "");
        int countFind = 0;
        for(int i = 0; i < employees.size(); i++) {
            for(int j = 0; j < newEmployee.size(); j++) {
                if(employees.get(i).getMIBEmployeeId() == newEmployee.get(j).getMIBEmployeeId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 4);

        //Delete test data
        for (int i = 0; i < newEmployee.size(); i++) {
            mIBEmployeeRepository.deleteById(newEmployee.get(i).getMIBEmployeeId());
            userService.deleteUserById(userRepository.findByUsername("testUsername" + (i+1)).get().getUserId());
        }
        roleRepository.deleteByRoleId(role1.getRoleId());
        roleRepository.deleteByRoleId(role2.getRoleId());
    }

    @Test
    void checkGetMIBEmployeeByFildsOne() {
        //Create test data
        Role role1 = CreateTestRole("testRole1");
        Role role2 = CreateTestRole("testRole2");
        List<MIBEmployee> newEmployee = new ArrayList<MIBEmployee>();
        newEmployee.add(CreateTestMIBEmployee("testName1", "10 age", "testDescription1", "testUsername1", "testPassword", (long)role1.getRoleId()));
        newEmployee.add(CreateTestMIBEmployee("testName2", "11 age", "testDescription1", "testUsername2", "testPassword", (long)role1.getRoleId()));
        newEmployee.add(CreateTestMIBEmployee("testName3", "10 age", "testDescription2", "testUsername3", "testPassword", (long)role1.getRoleId()));
        newEmployee.add(CreateTestMIBEmployee("testName4", "10 age", "testDescription1", "testUsername4", "testPassword", (long)role2.getRoleId()));

        //Test
        List<MIBEmployee> employees = hrManagerService.getMIBEmployeeByFilds("", "11 age", 0L, "");
        int countFind = 0;
        for(int i = 0; i < employees.size(); i++) {
            for(int j = 0; j < newEmployee.size(); j++) {
                if(employees.get(i).getMIBEmployeeId() == newEmployee.get(j).getMIBEmployeeId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);

        //Delete test data
        for (int i = 0; i < newEmployee.size(); i++) {
            mIBEmployeeRepository.deleteById(newEmployee.get(i).getMIBEmployeeId());
            userService.deleteUserById(userRepository.findByUsername("testUsername" + (i+1)).get().getUserId());
        }
        roleRepository.deleteByRoleId(role1.getRoleId());
        roleRepository.deleteByRoleId(role2.getRoleId());
    }

    @Test
    void checkGetMIBEmployeeByFildsAny() {
        //Create test data
        Role role1 = CreateTestRole("testRole1");
        Role role2 = CreateTestRole("testRole2");
        List<MIBEmployee> newEmployee = new ArrayList<MIBEmployee>();
        newEmployee.add(CreateTestMIBEmployee("testName1", "10 age", "testDescription1", "testUsername1", "testPassword", (long)role1.getRoleId()));
        newEmployee.add(CreateTestMIBEmployee("testName2", "11 age", "testDescription1", "testUsername2", "testPassword", (long)role1.getRoleId()));
        newEmployee.add(CreateTestMIBEmployee("testName3", "10 age", "testDescription2", "testUsername3", "testPassword", (long)role1.getRoleId()));
        newEmployee.add(CreateTestMIBEmployee("testName4", "10 age", "testDescription1", "testUsername4", "testPassword", (long)role2.getRoleId()));

        //Test
        List<MIBEmployee> employees = hrManagerService.getMIBEmployeeByFilds("", "10 age", 0L, "");
        int countFind = 0;
        for(int i = 0; i < employees.size(); i++) {
            for(int j = 0; j < newEmployee.size(); j++) {
                if(employees.get(i).getMIBEmployeeId() == newEmployee.get(j).getMIBEmployeeId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 3);

        //Delete test data
        for (int i = 0; i < newEmployee.size(); i++) {
            mIBEmployeeRepository.deleteById(newEmployee.get(i).getMIBEmployeeId());
            userService.deleteUserById(userRepository.findByUsername("testUsername" + (i+1)).get().getUserId());
        }
        roleRepository.deleteByRoleId(role1.getRoleId());
        roleRepository.deleteByRoleId(role2.getRoleId());
    }

    @Test
    void checkGetAllMIBEmployee() {
        int countEmployee = 0;
        List<MIBEmployee> employeeService = hrManagerService.getAllMIBEmployee();

        List<MIBEmployee> employeeRepository = new ArrayList<MIBEmployee>();
        mIBEmployeeRepository.findAll().iterator().forEachRemaining(employeeRepository::add);


        for(int i = 0; i < employeeRepository.size(); i++) {
            for(int j = 0; j < employeeService.size(); j++) {
                if (employeeRepository.get(i).getMIBEmployeeId() == employeeService.get(j).getMIBEmployeeId()) {
                    countEmployee++;
                }
            }
        }

        assertTrue(countEmployee == employeeRepository.size());
    }

    @Test
    void checkDeleteFreePersonaById() {
        //Create test data
        ArrayList<FreePersona> freePersonas = new ArrayList<FreePersona>();
        freePersonas.add(new FreePersona(1L, "testName1", "10 age", "testProfession1",  "testEducation", "testDescription"));

        //Test
        hrManagerService.deleteFreePersonaById(freePersonas, 1L);

        assertTrue(freePersonas.size() == 0);
    }

    @Test
    void checkGetAllFreePersonaByFildsAll() {
        //Create test data
        ArrayList<FreePersona> newFreePersonas = new ArrayList<FreePersona>();
        newFreePersonas.add(new FreePersona(1L, "testName1", "10 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(2L, "testName1", "10 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(3L, "testName2", "10 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(4L, "testName1", "11 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(5L, "testName1", "10 age", "testProfession2",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(6L, "testName1", "10 age", "testProfession1",  "testEducation2", "testDescription1"));
        newFreePersonas.add(new FreePersona(7L, "testName1", "10 age", "testProfession1",  "testEducation1", "testDescription2"));

        //Test
        List<FreePersona> freePersona = hrManagerService.getAllFreePersonaByFilds(newFreePersonas, new FreePersona(0L, "", "", "",  "", ""));
        int countFind = 0;
        for(int i = 0; i < freePersona.size(); i++) {
            for(int j = 0; j < newFreePersonas.size(); j++) {
                if(freePersona.get(i).getFreePersonaId() == newFreePersonas.get(j).getFreePersonaId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 7);
    }

    @Test
    void checkGetAllFreePersonaByFildsOne() {
        //Create test data
        ArrayList<FreePersona> newFreePersonas = new ArrayList<FreePersona>();
        newFreePersonas.add(new FreePersona(1L, "testName1", "10 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(2L, "testName1", "10 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(3L, "testName2", "10 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(4L, "testName1", "11 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(5L, "testName1", "10 age", "testProfession2",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(6L, "testName1", "10 age", "testProfession1",  "testEducation2", "testDescription1"));
        newFreePersonas.add(new FreePersona(7L, "testName1", "10 age", "testProfession1",  "testEducation1", "testDescription2"));

        //Test
        List<FreePersona> freePersona = hrManagerService.getAllFreePersonaByFilds(newFreePersonas, new FreePersona(0L, "", "", "testProfession2",  "", ""));
        int countFind = 0;
        for(int i = 0; i < freePersona.size(); i++) {
            for(int j = 0; j < newFreePersonas.size(); j++) {
                if(freePersona.get(i).getFreePersonaId() == newFreePersonas.get(j).getFreePersonaId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 1);
    }

    @Test
    void checkGetAllFreePersonaByFildsAny() {
        //Create test data
        ArrayList<FreePersona> newFreePersonas = new ArrayList<FreePersona>();
        newFreePersonas.add(new FreePersona(1L, "testName1", "10 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(2L, "testName1", "10 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(3L, "testName2", "10 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(4L, "testName1", "11 age", "testProfession1",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(5L, "testName1", "10 age", "testProfession2",  "testEducation1", "testDescription1"));
        newFreePersonas.add(new FreePersona(6L, "testName1", "10 age", "testProfession1",  "testEducation2", "testDescription1"));
        newFreePersonas.add(new FreePersona(7L, "testName1", "10 age", "testProfession1",  "testEducation1", "testDescription2"));

        //Test
        List<FreePersona> freePersona = hrManagerService.getAllFreePersonaByFilds(newFreePersonas, new FreePersona(0L, "", "", "",  "testEducation1", ""));
        int countFind = 0;
        for(int i = 0; i < freePersona.size(); i++) {
            for(int j = 0; j < newFreePersonas.size(); j++) {
                if(freePersona.get(i).getFreePersonaId() == newFreePersonas.get(j).getFreePersonaId()) {
                    countFind++;
                }
            }
        }

        assertTrue(countFind == 6);
    }

    Role CreateTestRole(String roleName) {
        Role role = roleRepository.findByRoleName(roleName).orElse(null);

        if(role != null) {
            List<User> buff = userRepository.findAllByRoles(role.getRoleName());
            for(int i = 0; i < buff.size(); i++) {
                userRepository.deleteById(buff.get(i).getUserId());
            }
            roleRepository.deleteByRoleId(role.getRoleId());
        }
        return roleRepository.save(new Role(roleName));
    }

    MIBEmployee CreateTestMIBEmployee(String name, String age, String description, String username, String password, Long idRole) {
        MIBEmployee employee = mIBEmployeeRepository.findAllByName(name).orElse(null);
        if(employee != null) {
            mIBEmployeeRepository.deleteById(employee.getMIBEmployeeId());
        }

        User buffUser = userService.saveUser(new User(0L, username, password, false), idRole, true);
        MIBEmployee buff = new MIBEmployee(name, age, description, username, password, idRole, null);
        buff.setIdUser(buffUser.getUserId());

        return mIBEmployeeRepository.save(buff);
    }

}
