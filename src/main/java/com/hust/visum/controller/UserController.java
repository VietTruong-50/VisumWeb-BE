package com.hust.visum.controller;

import com.hust.visum.model.Favorite;
import com.hust.visum.model.Playlist;
import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import com.hust.visum.request.PlaylistDTO;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.service.implement.PlaylistServiceImpl;
import com.hust.visum.service.implement.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/visum")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PlaylistServiceImpl playlistService;

    @GetMapping(path = "/favorite")
    public ApiResponse<Page<Song>> getListFavorites(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ApiResponse.successWithResult(userDetailsService.getFavoriteList(authentication.getName(), page, size, sortBy));
    }

    @GetMapping("/user")
    public ApiResponse<User> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ApiResponse.successWithResult(userDetailsService.getCurrentUser(authentication.getName()));
    }

    @PostMapping(value = "/playlist", produces = "application/json")
    public ApiResponse<Playlist> createNewPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        return ApiResponse.successWithResult(playlistService.createNewPlaylist(playlistDTO));
    }

    @PostMapping(value = "/playlist/{id}", produces = "application/json")
    public ApiResponse<?> addSongToPlaylist(@PathVariable("id") Long playlistId, @RequestBody List<Long> listId) {
        playlistService.addSongToPlaylist(playlistId, listId);
        return ApiResponse.successWithResult(null, "Add success");
    }

    @DeleteMapping(value = "/playlist/{id}", produces = "application/json")
    public ApiResponse<?> deleteSongFromPlaylist(@PathVariable("id") Long playlistId, @RequestBody List<Long> listId) {
        playlistService.removeSongFromPlaylist(playlistId, listId);
        return ApiResponse.successWithResult(null, "Remove success");
    }

    @GetMapping(value = "/playlist/{id}", produces = "application/json")
    public ApiResponse<Set<Song>> getPlaylist(@PathVariable("id") Long playlistId) {
        return ApiResponse.successWithResult(playlistService.getPlaylistSong(playlistId), "Remove success");
    }
}
