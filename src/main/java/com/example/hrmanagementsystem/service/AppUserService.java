package com.example.hrmanagementsystem.service;

import com.example.hrmanagementsystem.exceptions.user.UserAlreadyExistException;
import com.example.hrmanagementsystem.mapper.FromEntityToResponse;
import com.example.hrmanagementsystem.mapper.FromRequestToEntity;
import com.example.hrmanagementsystem.model.entity.AppUser;
import com.example.hrmanagementsystem.model.entity.Role;
import com.example.hrmanagementsystem.model.enums.ERRORCODE;
import com.example.hrmanagementsystem.model.request.user.AppUserLoginResponse;
import com.example.hrmanagementsystem.model.request.user.AppUserRequest;
import com.example.hrmanagementsystem.model.response.user.UserResponse;
import com.example.hrmanagementsystem.model.response.user.UserTokenResponse;
import com.example.hrmanagementsystem.repository.AppUserRepository;
import com.example.hrmanagementsystem.repository.RoleRepository;
import com.example.hrmanagementsystem.security.UserSecurity;
import com.example.hrmanagementsystem.security.jwt.JwtService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    //Security Method
    public AppUser findUserByUserName(String name){
        return appUserRepository.findByUserName(name).orElseThrow(()-> new  UsernameNotFoundException("User not found"));
    }

    public UserResponse register(AppUserRequest appUserRequest) {
        Optional<AppUser> byUserName =
                appUserRepository.findByUserName(appUserRequest.getUserName());

       if (byUserName.isPresent()){
           throw new UserAlreadyExistException(ERRORCODE.USER_ALREADY_FOUND_EXCEPTION);
       }

        AppUser appUser =FromRequestToEntity.fromAppUserRequestToEntity(appUserRequest);

       if (appUser.getRole() == null || appUser.getRole().isEmpty()){
          Role role = roleService.findByRole("USER");
          if (role == null){
              role = new Role();
              role.setRole("USER");
             role = roleService.save(role);
          }
          appUser.getRole().add(role);
       }

       appUser.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));

        AppUser savedUser = appUserRepository.save(appUser);

        UserDetails userDetails = new UserSecurity(savedUser);
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        log.info("User is registered. User Id: {}, User Name: {}", appUser.getId(), appUser.getUserName());

        UserResponse userResponse = FromEntityToResponse.fromUserEntityToUserResponse(savedUser);
        userResponse.setAccessToken(accessToken);
        userResponse.setRefreshToken(refreshToken);
        return userResponse;

    }

    public UserTokenResponse login(AppUserLoginResponse appUserLoginResponse) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        appUserLoginResponse.getUserName(),
                        appUserLoginResponse.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
}
