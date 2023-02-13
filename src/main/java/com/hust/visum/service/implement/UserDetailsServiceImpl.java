package com.hust.visum.service.implement;

import com.hust.visum.Jwt.JwtUtils;
import com.hust.visum.model.Favorite;
import com.hust.visum.model.Role;
import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import com.hust.visum.repository.FavoriteRepository;
import com.hust.visum.repository.RoleRepository;
import com.hust.visum.repository.SongRepository;
import com.hust.visum.repository.UserRepository;
import com.hust.visum.request.LoginRequest;
import com.hust.visum.request.PasswordDTO;
import com.hust.visum.request.UserDTO;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.JwtResponse;
import com.hust.visum.response.MessageResponse;
import com.hust.visum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

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
    public ApiResponse<User> register(UserDTO signupRequest) {

        //Check if existed username or email
        if (userRepository.existsByUserName(signupRequest.getUserName())) {
            return ApiResponse.failureWithCode("", new MessageResponse("Error: Username is already taken!").getMessage());
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ApiResponse.failureWithCode("", new MessageResponse("Error: Email is already taken!").getMessage());
        }

        User user = new User(
                signupRequest.getUserName(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getEmail());
//        User user = new User();

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

        return ApiResponse.successWithResult(userRepository.save(signupRequest.toUser(user)));
    }

    @Override
    public User getCurrentUser(String userName) {
        return userRepository.findUserByUserName(userName).orElse(null);
    }

    @Override
    public User changePassword(Long userId, PasswordDTO passwordDTO) {
        Optional<User> user = userRepository.findById(userId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (user.isPresent()) {
            User updateUser = user.get();
            if (encoder.matches(passwordDTO.getCurrentPassword(), updateUser.getPassword())) {
                updateUser.setPassword(new BCryptPasswordEncoder().encode(passwordDTO.getNewPassword()));
                return userRepository.save(updateUser);
            }
        }
        return null;
    }

    @Override
    public User updateUser(Long userId, UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userId);
        User updateUser;
        if (user.isPresent()) {
            updateUser = user.get();

            updateUser.setLastName(userDTO.getLastName() != null ? userDTO.getLastName() : updateUser.getLastName());
            updateUser.setFirstName(userDTO.getFirstName() != null ? userDTO.getFirstName() : updateUser.getFirstName());
            updateUser.setGenderEnum(userDTO.getGender() != null ? userDTO.getGender() : updateUser.getGenderEnum());
            updateUser.setEmail(userDTO.getEmail() != null ? userDTO.getEmail() : updateUser.getEmail());
            updateUser.setMobile(userDTO.getMobile() != null ? userDTO.getMobile() : updateUser.getMobile());
            updateUser.setBirthOfDate(userDTO.getBirthOfDate() != null ? userDTO.getBirthOfDate() : updateUser.getBirthOfDate());

            return userRepository.save(updateUser);
        }
        return null;
    }

    @Override
    public Page<Song> getFavoriteList(String userName, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        List<Song> songs = songRepository.findFavoriteListByUser(userName);

        return new PageImpl<>(songs, pageable, songs.size());
    }


    @Override
    public Song removeFavoriteSong(Long songId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findUserByUserName(authentication.getName()).orElse(null);

        Song song = songRepository.findById(songId).orElse(null);


        if (song != null && user != null) {
            favoriteRepository.deleteBySongAndUser(song, user);
        }

        return song;
    }

    @Override
    public Song addFavoriteSong(Long songId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findUserByUserName(authentication.getName()).orElse(null);

        Song song = songRepository.findById(songId).orElse(null);

        if (!favoriteRepository.existsBySong(song)) {
            if (song != null && user != null) {
                Favorite favorite = new Favorite();

                favorite.setSong(song);
                favorite.setUser(user);

                favoriteRepository.save(favorite);
            }
        }

        return song;
    }
}
