package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.hrmanager.RoleRepository;
import com.itmo.mibsystem.dao.hrmanager.UserRepository;
import com.itmo.mibsystem.model.Role;
import com.itmo.mibsystem.model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.itmo.mibsystem.model.researcher.SourceTechnology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author BaladKV
 * @since 08.11.2020
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public Optional<User> findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public List<User> getAgent() {
        return userRepository.findAllByRoles("OP_AGENT");
    }


    public User saveUser(User user, Role.Type role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAccountLocked(false);
        user.setAccountExpired(false);
        user.setDisabled(false);

        Role roles = roleRepository.findByRoleName(role.name()).orElse(new Role("NONE"));
        user.setRoles(new ArrayList<>(Collections.singletonList(roles)));

        return userRepository.save(user);
    }

}
