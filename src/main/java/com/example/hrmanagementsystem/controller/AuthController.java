package com.example.hrmanagementsystem.controller;

import com.example.hrmanagementsystem.model.CommonResponse;
import com.example.hrmanagementsystem.model.request.user.AppUserLoginResponse;
import com.example.hrmanagementsystem.model.request.user.AppUserRequest;
import com.example.hrmanagementsystem.model.response.user.UserResponse;
import com.example.hrmanagementsystem.model.response.user.UserTokenResponse;
import com.example.hrmanagementsystem.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AppUserService appUserService;


    @PostMapping("/register")
    public ResponseEntity<CommonResponse<UserResponse>> register(@RequestBody AppUserRequest appUserRequest){
        UserResponse register = appUserService.register(appUserRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.success(register.getUserName()+
                        "adli istifadeci muveffeqiyyetle qeydiyyatdan kecmisdir..", register));
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> login(@RequestBody AppUserLoginResponse appUserLoginResponse){
           UserTokenResponse userTokenResponse =  appUserService.login(appUserLoginResponse);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userTokenResponse);
    }
}
