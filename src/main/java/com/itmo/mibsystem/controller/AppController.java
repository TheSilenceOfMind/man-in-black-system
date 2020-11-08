package com.itmo.mibsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author BaladKV
 * @since 04.06.2020
 */
@Controller
public class AppController {

    @GetMapping("/login")
    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping({"/", "/index"})
    public String listAllDocs(
        @RequestParam(name = "crit_1", required = false) String cat1,
        @RequestParam(name = "crit_2", required = false) String cat2,
        @RequestParam(name = "crit_3", required = false) String cat3,
        @RequestParam(name = "keywords", required = false) String keywords,
        Model model) {

        return "index";
    }

    @PostMapping("/index")
    public String index(
        @RequestParam(name = "hidden_id") Long id,
        @RequestParam(name = "field_1") String field1,
        @RequestParam(name = "field_2") String field2,
        @RequestParam(name = "field_3") String field3,
        @RequestParam(name = "crit_1") String cat1,
        @RequestParam(name = "crit_2") String cat2,
        @RequestParam(name = "crit_3") String cat3,
        @RequestParam(name = "description") String desc,
        @RequestParam(name = "remove_flag", required = false) String isRemoving
    ) {
        return "redirect:/index";
    }

}
