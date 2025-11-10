package com.example.hrmanagementsystem.config.init;

import com.example.hrmanagementsystem.model.entity.AppUser;
import com.example.hrmanagementsystem.model.entity.Role;
import com.example.hrmanagementsystem.repository.AppUserRepository;
import com.example.hrmanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2) // Run after RoleInitializer which has default order of 1
public class AdminUserInitializer implements CommandLineRunner {

    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initAdminUser();
    }

    private void initAdminUser() {
        log.info("Initializing admin user...");

        // Check if admin user already exists
        Optional<AppUser> existingAdmin = userRepository.findByUserName("admin");
        if (existingAdmin.isPresent()) {
            log.info("Admin user already exists");
            return;
        }

        // Get the ADMIN role (should be created by RoleInitializer)
        Optional<Role> adminRole = roleRepository.findByRoleIgnoreCase("ADMIN");
        if (adminRole.isEmpty()) {
            log.error("ADMIN role not found. Cannot create admin user.");
            return;
        }

        // Create new admin user
        AppUser adminUser =new AppUser();
        adminUser.setUserName("admin");
        adminUser.setPassword(passwordEncoder.encode("1234"));

        Set<Role> roles = new HashSet<>();
        Role role = adminRole.get();
        roles.add(role);
        adminUser.setRole(roles);

        userRepository.save(adminUser);
        log.info("Admin user created successfully with username 'admin' and password '1234'");
    }

}
