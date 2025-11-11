package com.example.hrmanagementsystem.model.request.user;

import com.example.hrmanagementsystem.model.entity.Role;
import com.example.hrmanagementsystem.model.request.role.RoleRequest;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AppUserRequest {

    private String userName;

    private String password;

}
