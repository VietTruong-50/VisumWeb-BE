package com.hust.visum.service.implement;

import com.hust.visum.model.Playlist;
import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import com.hust.visum.repository.PlaylistRepository;
import com.hust.visum.repository.SongRepository;
import com.hust.visum.repository.UserRepository;
import com.hust.visum.request.PlaylistDTO;
import com.hust.visum.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    @Override
    public Playlist createNewPlaylist(PlaylistDTO playlistDTO) {
        Optional<User> user = userRepository.findById(playlistDTO.getUserId());

        if (user.isPresent()) {
            Playlist playlist = new Playlist();

            playlist.setUser(user.get());
            playlist.setPlaylistName(playlistDTO.getPlaylistName());

            return playlistRepository.save(playlist);
        }
        return null;
    }

    @Override
    public void addSongToPlaylist(Long playListId, List<Long> listId) {
        playlistRepository.findById(playListId).ifPresent(playlist -> listId.forEach(id -> {
            Song song = songRepository.findById(id).orElse(null);
            if (song != null) {

                playlist.getSongList().add(song);

                song.getPlaylists().add(playlist);

                songRepository.save(song);
            }
        }));

    }

    @Override
    public void removeSongFromPlaylist(Long playListId, List<Long> listId) {
        playlistRepository.findById(playListId).ifPresent(playlist -> listId.forEach(id -> {
            Song song = songRepository.findById(id).orElse(null);
            if (song != null) {

                playlist.getSongList().remove(song);

                song.getPlaylists().remove(playlist);

                songRepository.save(song);
            }
        }));
    }

    @Override
    public Set<Song> getPlaylistSong(Long playlistId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);

        return playlist.map(Playlist::getSongList).orElse(null);
    }
}
