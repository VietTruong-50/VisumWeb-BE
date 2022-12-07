package com.hust.visum.service;

import com.hust.visum.model.Playlist;
import com.hust.visum.model.Song;
import com.hust.visum.request.PlaylistDTO;

import java.util.List;
import java.util.Set;

public interface PlaylistService {
    Playlist createNewPlaylist(PlaylistDTO playlistDTO);

    void addSongToPlaylist(Long playListId, List<Long> listId);

    void removeSongFromPlaylist(Long playListId, List<Long> listId);

    Set<Song> getPlaylistSong(Long playlistId);
}
