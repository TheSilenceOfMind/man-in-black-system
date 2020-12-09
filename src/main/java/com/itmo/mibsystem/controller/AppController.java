package com.itmo.mibsystem.controller;

import com.itmo.mibsystem.model.Role;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author BaladKV
 * @since 04.06.2020
 */
@Controller
public class AppController {

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping({"/", "/index"})
    public String openSpecificIndexPage() throws Exception {
        return getPathPrefix(getRole()) + "/index";
    }

    /**
     * Получить роль из контекста приложения.
     * @return роль
     * @throws Exception если роль отсутствует, либо не присутствует в Role.Type
     */
    private Role.Type getRole() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> roles = new ArrayList<>(auth.getAuthorities());
        if (roles.size() == 0) {
            throw new Exception("Roles are missed for user " + auth.getPrincipal().toString());
        }
        try {
            return Role.Type.valueOf(roles.get(0).getAuthority());
        } catch (IllegalArgumentException e) {
            throw new Exception("Role doesn't exist for user " + auth.getPrincipal().toString(), e);
        }
    }

    private String getPathPrefix(Role.Type role) {
        switch (role) {
            case PASSPORTER:
                return "passporter";
            case HR:
                return "hr";
            case ADMIN:
                return "admin";
            case LAWYER:
                return "lawyer";
            case OP_AGENT:
                return "agent";
            case RESEARCHER:
                return "researcher";
            case TECHNOLOGIST:
                return "technologist";
            default:
                throw new IllegalArgumentException("Role " + role + " doesn't exist!");
        }
    }
}
