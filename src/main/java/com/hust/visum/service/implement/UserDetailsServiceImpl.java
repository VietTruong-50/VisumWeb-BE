package com.hust.visum.service.implement;

import com.hust.visum.Jwt.JwtUtils;
import com.hust.visum.model.Role;
import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import com.hust.visum.repository.RoleRepository;
import com.hust.visum.repository.SongRepository;
import com.hust.visum.repository.UserRepository;
import com.hust.visum.request.LoginRequest;
import com.hust.visum.request.PasswordDTO;
import com.hust.visum.request.SignupRequest;
import com.hust.visum.request.UserDTO;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.JwtResponse;
import com.hust.visum.response.MessageResponse;
import com.hust.visum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserService, UserDetailsService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Get token
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

    @Override
    public ApiResponse<User> register(SignupRequest signupRequest) {

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

    @Override
    public User changePassword(String userId, PasswordDTO passwordDTO) {
        return null;
    }

    @Override
    public User updateUser(String userId, UserDTO userDTO) {
        return null;
    }

    @Override
    public Page<Song> getFavoriteList(String userId, int page, int size, String sortBy) {
        return null;
    }

    @Override
    public User removeFavoriteSong(String userId, String songId) {
        return null;
    }
}
