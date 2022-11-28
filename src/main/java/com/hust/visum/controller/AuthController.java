package com.hust.visum.controller;

import com.hust.visum.model.User;
import com.hust.visum.request.LoginRequest;
import com.hust.visum.request.SignupRequest;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.JwtResponse;
import com.hust.visum.service.implement.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/signin", produces = "application/json")
    public ApiResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ApiResponse.successWithResult(userDetailsService.authenticateUser(loginRequest));
    }

    @PostMapping(value = "/signup", produces = "application/json")
    public ApiResponse<User> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        return userDetailsService.register(signupRequest);
    }
}
