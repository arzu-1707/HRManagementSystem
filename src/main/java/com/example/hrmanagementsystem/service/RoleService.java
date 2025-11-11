package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.model.entity.Role;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import com.example.hrmanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;


    public Role findByRole(String user) {
        return roleRepository.findByRoleIgnoreCase(user).orElseThrow();
    }

    public Role save(Role role) {
       return roleRepository.save(role);
    }
}
