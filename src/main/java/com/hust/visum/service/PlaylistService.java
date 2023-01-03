package com.hust.visum.service;

import com.hust.visum.model.Playlist;
import com.hust.visum.request.PlaylistDTO;
import com.hust.visum.response.PlaylistResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlaylistService {
    Playlist createNewPlaylist(PlaylistDTO playlistDTO);

    void updatePlaylist(Long playlistId, PlaylistDTO playlistDTO);

    void deletePlaylist(Long playlistId);

    void addSongToPlaylist(Long playListId, List<Long> listId);

    void removeSongFromPlaylist(Long playListId, List<Long> listId);

    PlaylistResponse getPlaylistSong(Long playlistId);

    List<PlaylistResponse> findAllByUser();

    Page<PlaylistResponse> findPlaylistsByTitle(String title, int page, int size);
}
