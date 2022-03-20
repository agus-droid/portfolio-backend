package com.agus.portfolio.utils;

import com.agus.portfolio.security.service.RolService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    final
    RolService rolService;

    public CreateRoles(RolService rolService) {
        this.rolService = rolService;
    }

    @Override
    public void run(String... args) throws Exception {
        /** Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
         */
    }
}