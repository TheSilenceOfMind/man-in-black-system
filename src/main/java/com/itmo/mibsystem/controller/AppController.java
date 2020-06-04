package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.service.RepositoryManager;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
