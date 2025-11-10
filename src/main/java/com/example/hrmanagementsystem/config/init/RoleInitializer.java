package com.example.hrmanagementsystem.config.init;

import com.example.hrmanagementsystem.model.entity.Role;
import com.example.hrmanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1) // Run before AdminUserInitializer
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        initRoles();
    }

    private void initRoles() {
        log.info("Initializing roles...");
        if (roleRepository.findByRoleIgnoreCase("USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setRole("USER");
            roleRepository.save(userRole);
            log.info("USER role created");
        } else {
            log.info("USER role already exists");
        }
        if (roleRepository.findByRoleIgnoreCase("ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setRole("ADMIN");
            roleRepository.save(adminRole);
            log.info("ADMIN role created");
        } else {
            log.info("ADMIN role already exists");
        }
        log.info("Role initialization completed");
    }
}
