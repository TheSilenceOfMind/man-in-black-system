package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.daos.UserRepository;
import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.service.RepositoryManager;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BaladKV
 * @since 04.06.2020
 */
@Controller
public class AppController {

    @Autowired
    RepositoryManager repositoryManager;

    @GetMapping("/get")
    public String greeting(
        @RequestParam(name = "entityId", required = false, defaultValue = "0") Long entityId,
        @RequestParam(name="roleId") Long roleId,
        Model model
    ) throws Exception {
        CrudRepository<?, Long> repository = repositoryManager.getRepository(roleId);
        Optional<?> val = repository.findById(entityId);
        model.addAttribute("value", val.orElse(null));

        return "hi";
    }

    @PostMapping("/login")
    public String login(
        @RequestParam String username,
        @RequestParam String password,
        Model model
    ) throws Exception {
        UserRepository repository = repositoryManager.getUserRepository();
        List<User> userList = repository.findByUsernameStartsWithIgnoreCase(username);
        if (userList.isEmpty()) {
            model.addAttribute("error", "user not found");
            return "login";
        }
        if (!password.equals(userList.get(0).getPassword())) {
            model.addAttribute("error", "password is incorrect");
            return "login";
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
