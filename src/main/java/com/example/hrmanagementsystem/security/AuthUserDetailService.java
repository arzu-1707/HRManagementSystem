package com.example.hrmanagementsystem.security;

import com.example.hrmanagementsystem.model.entity.AppUser;
import com.example.hrmanagementsystem.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailService implements UserDetailsService {

    private final AppUserService appUserService;

    public AuthUserDetailService(@Lazy AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser userByUserName = appUserService.findUserByUserName(username);
        return new UserSecurity(userByUserName);
    }
}
