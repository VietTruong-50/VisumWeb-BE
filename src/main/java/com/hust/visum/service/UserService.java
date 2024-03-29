package com.hust.visum.service;

import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import com.hust.visum.request.LoginRequest;
import com.hust.visum.request.PasswordDTO;
import com.hust.visum.request.UserDTO;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.JwtResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    ApiResponse<User> register(UserDTO signupRequest);

    User getCurrentUser(String userName);

    User changePassword(PasswordDTO passwordDTO) ;

    User updateUser(UserDTO userDTO) ;

    Page<Song> getFavoriteList(String userName, int page, int size, String sortBy);

    Song removeFavoriteSong( Long songId);

    Song addFavoriteSong(Long songId);
}
