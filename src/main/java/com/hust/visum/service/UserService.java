package com.hust.visum.service;

import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import com.hust.visum.request.LoginRequest;
import com.hust.visum.request.PasswordDTO;
import com.hust.visum.request.SignupRequest;
import com.hust.visum.request.UserDTO;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.JwtResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    ApiResponse<User> register(SignupRequest signupRequest);
    User changePassword(String userId, PasswordDTO passwordDTO) ;
    User updateUser(String userId, UserDTO userDTO) ;
    Page<Song> getFavoriteList(String userId, int page, int size, String sortBy);
    User removeFavoriteSong(String userId, String songId);
}
