package com.hust.visum.controller;

import com.hust.visum.Jwt.JwtUtils;
import com.hust.visum.model.Role;
import com.hust.visum.model.User;
import com.hust.visum.repository.RoleRepository;
import com.hust.visum.repository.UserRepository;
import com.hust.visum.request.LoginRequest;
import com.hust.visum.request.SignupRequest;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.JwtResponse;
import com.hust.visum.response.MessageResponse;
import com.hust.visum.service.implement.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/signin", produces = "application/json")
    public ApiResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Get token
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return ApiResponse.successWithResult(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping(value = "/signup", produces = "application/json")
    public ApiResponse<User> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        //Check if existed username or email
        if (userRepository.existsByUserName(signupRequest.getUsername())) {
            return ApiResponse.failureWithCode("", new MessageResponse("Error: Username is already taken!").getMessage());
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ApiResponse.failureWithCode("", new MessageResponse("Error: Email is already taken!").getMessage());
        }

        User user = new User(
                signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getEmail());

        //Get role from request
        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        //Add roles to user
        if (strRoles == null) {
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(
                    role -> {
                        switch (role) {
                            case "admin" -> {
                                Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(adminRole);
                            }
                            case "mod" -> {
                                Role modRole = roleRepository.findByName("ROLE_MODERATOR")
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(modRole);
                            }
                            default -> {
                                Role userRole = roleRepository.findByName("ROLE_USER")
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(userRole);
                            }
                        }
                    }
            );
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ApiResponse.successWithResult(user);
    }
}
