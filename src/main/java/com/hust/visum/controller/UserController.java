package com.hust.visum.controller;

import com.hust.visum.model.Playlist;
import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import com.hust.visum.model.Comment;
import com.hust.visum.request.CommentDTO;
import com.hust.visum.request.PlaylistDTO;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.PlaylistResponse;
import com.hust.visum.service.implement.PlaylistServiceImpl;
import com.hust.visum.service.implement.SongServiceImpl;
import com.hust.visum.service.implement.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visum")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PlaylistServiceImpl playlistService;

    @Autowired
    private SongServiceImpl songService;

    @GetMapping(path = "/favorite", produces = "application/json")
    public ApiResponse<Page<Song>> getListFavorites(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ApiResponse.successWithResult(userDetailsService.getFavoriteList(authentication.getName(), page, size, sortBy));
    }

    @PostMapping(path = "/favorite/{songId}", produces = "application/json")
    public ApiResponse<Song> addFavoriteSong(@PathVariable("songId") Long songId) {
        return ApiResponse.successWithResult(userDetailsService.addFavoriteSong(songId));
    }

    @DeleteMapping(path = "/favorite/{songId}", produces = "application/json")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ApiResponse<Song> deleteFavoriteSong(@PathVariable("songId") Long songId) {
        return ApiResponse.successWithResult(userDetailsService.removeFavoriteSong(songId));
    }

    @GetMapping(path = "/user", produces = "application/json")
    public ApiResponse<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ApiResponse.successWithResult(userDetailsService.getCurrentUser(authentication.getName()));
    }

    @PostMapping(value = "/playlist", produces = "application/json")
    public ApiResponse<Playlist> createNewPlaylist(@RequestBody() PlaylistDTO playlistDTO) {
        return ApiResponse.successWithResult(playlistService.createNewPlaylist(playlistDTO));
    }

    @PutMapping(value = "/playlist/{playlistId}", produces = "application/json")
    public ApiResponse<?> updatePlaylist(@PathVariable("playlistId") Long playlistId, @RequestBody() PlaylistDTO playlistDTO) {
        playlistService.updatePlaylist(playlistId, playlistDTO);
        return ApiResponse.successWithResult("Update success");
    }

    @PostMapping(value = "/playlist/{id}", produces = "application/json")
    public ApiResponse<?> addSongToPlaylist(@PathVariable("id") Long playlistId, @RequestBody List<Long> listId) {
        playlistService.addSongToPlaylist(playlistId, listId);
        return ApiResponse.successWithResult(null, "Add success");
    }

    @DeleteMapping(value = "/playlist/{id}", produces = "application/json")
    public ApiResponse<?> deleteSongsFromPlaylist(@PathVariable("id") Long playlistId, @RequestBody List<Long> listId) {
        playlistService.removeSongFromPlaylist(playlistId, listId);
        return ApiResponse.successWithResult(null, "Remove success");
    }

    @DeleteMapping(value = "/playlist/{id}/song/{songId}", produces = "application/json")
    public ApiResponse<?> deleteSongFromPlaylist(@PathVariable("id") Long playlistId, @PathVariable("songId") Long songId) {
        playlistService.removeSongFromPlaylist(playlistId, songId);
        return ApiResponse.successWithResult(null, "Remove success");
    }

    @GetMapping(value = "/playlist/{id}", produces = "application/json")
    public ApiResponse<PlaylistResponse> getPlaylistById(@PathVariable("id") Long playlistId, @RequestParam String orderBy, @RequestParam String sortType) {
        return ApiResponse.successWithResult(playlistService.getPlaylistSong(playlistId, orderBy, sortType));
    }

    @GetMapping(value = "/playlists", produces = "application/json")
    public ApiResponse<List<PlaylistResponse>> getAllPlaylistByUser() {
        return ApiResponse.successWithResult(playlistService.findAllByUser());
    }

    @GetMapping(value ="/playlists/{playlistId}/songs", produces = "application/json")
    public ApiResponse<Page<Song>> findSongsNotInPlaylist(@PathVariable("playlistId") Long playlistId, @RequestParam int page, @RequestParam int size){
        return ApiResponse.successWithResult(playlistService.findSongNotInPlaylist(playlistId, page, size));
    }

    @DeleteMapping(value = "/playlists/{playlistId}", produces = "application/json")
    public ApiResponse<Playlist> deletePlaylist(@PathVariable("playlistId") Long playlistId) {
        playlistService.deletePlaylist(playlistId);
        return ApiResponse.successWithResult(null, "Delete success");
    }

    @GetMapping(value = "/searchPlaylists", produces = "application/json")
    public ApiResponse<Page<PlaylistResponse>> findPlaylistsByTitle(@RequestParam String title,
                                                                    @RequestParam int page,
                                                                    @RequestParam int size) {
        Page<PlaylistResponse> playlistResponses = playlistService.findPlaylistsByTitle(title, page, size);
        return ApiResponse.successWithResult(playlistResponses);
    }

    @GetMapping(value = "/comments/{songId}", produces = "application/json")
    public ApiResponse<Page<CommentDTO>> getAllComments(@PathVariable("songId") Long songId,
                                                        @RequestParam String sortBy,
                                                        @RequestParam String orderBy,
                                                        @RequestParam int page,
                                                        @RequestParam int size) {
        return ApiResponse.successWithResult(songService.getCommentPagination(songId, page, size, sortBy, orderBy));
    }

    @PostMapping(value = "/comment", produces = "application/json")
    public ApiResponse<Comment> createCommentDTO(@RequestBody CommentDTO commentDTO) {
        return ApiResponse.successWithResult(songService.createComment(commentDTO));
    }

    @DeleteMapping(value = "/comment/{id}", produces = "application/json")
    public ApiResponse<Comment> deleteComment(@PathVariable Long id) {
        return ApiResponse.successWithResult(this.songService.deleteComment(id), "Delete Comment Successfully");
    }
}
