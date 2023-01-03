package com.hust.visum.controller;

import com.hust.visum.model.User;
import com.hust.visum.request.LoginRequest;
import com.hust.visum.request.SignupRequest;
import com.hust.visum.request.UserDTO;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.JwtResponse;
import com.hust.visum.service.implement.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/signin", produces = "application/json")
    public ApiResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ApiResponse.successWithResult(userDetailsService.authenticateUser(loginRequest));
    }

    @PostMapping(value = "/signup", produces = "application/json")
    public ApiResponse<User> registerUser(@Valid @RequestBody UserDTO signupRequest) {
        return userDetailsService.register(signupRequest);
    }
}
