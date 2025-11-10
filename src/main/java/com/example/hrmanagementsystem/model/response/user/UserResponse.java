package com.example.hrmanagementsystem.model.response.user;

import com.example.hrmanagementsystem.model.response.role.RoleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserResponse {

    private Long id;
    private String userName;

    private Set<RoleResponse> roles;

    private String accessToken;
    private String refreshToken;

}
