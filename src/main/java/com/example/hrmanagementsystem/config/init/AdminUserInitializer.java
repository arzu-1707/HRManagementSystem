package com.example.hrmanagementsystem.config.init;

import com.example.hrmanagementsystem.model.entity.AppUser;
import com.example.hrmanagementsystem.model.entity.Role;
import com.example.hrmanagementsystem.repository.AppUserRepository;
import com.example.hrmanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class AdminUserInitializer implements CommandLineRunner {

    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initAdminUser();
    }

    private void initAdminUser() {
        log.info("🔹 Initializing admin user...");

        Optional<AppUser> existingAdmin = userRepository.findByUserName("admin");
        if (existingAdmin.isPresent()) {
            log.info("✅ Admin user already exists");
            return;
        }

        // ADMIN rolunu tapırıq
        Role adminRole = roleRepository.findByRoleIgnoreCase("ADMIN")
                .orElseThrow(() -> new RuntimeException("ADMIN role not found in DB!"));

        // Bu sətir detached xətasını aradan qaldırır
        adminRole = roleRepository.saveAndFlush(adminRole);

        // Yeni admin yaradırıq
        AppUser adminUser = new AppUser();
        adminUser.setUserName("admin");
        adminUser.setPassword(passwordEncoder.encode("1234"));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        adminUser.setRole(roles);

        userRepository.save(adminUser);
        log.info("Admin user created successfully with username='admin' and password='1234'");
    }
}
