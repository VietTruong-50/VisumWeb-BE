package com.hust.visum.service.implement;

import com.hust.visum.model.Playlist;
import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import com.hust.visum.repository.PlaylistRepository;
import com.hust.visum.repository.SongRepository;
import com.hust.visum.repository.UserRepository;
import com.hust.visum.request.PlaylistDTO;
import com.hust.visum.response.PlaylistResponse;
import com.hust.visum.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> user = userRepository.findUserByUserName(authentication.getName());

        if (user.isPresent()) {
            Playlist playlist = new Playlist();

            playlist.setUser(user.get());
            playlist.setPlaylistName(playlistDTO.getPlaylistName());

            return playlistRepository.save(playlist);
        }
        return null;
    }

    @Override
    public void updatePlaylist(Long playlistId, PlaylistDTO playlistDTO) {
        playlistRepository.findById(playlistId).ifPresent(playlist ->
                {
                    playlist.setPlaylistName(playlistDTO.getPlaylistName());
                    playlistRepository.save(playlist);
                }
        );
    }

    @Override
    public void deletePlaylist(Long playlistId) {
        playlistRepository.deleteById(playlistId);
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
    public PlaylistResponse getPlaylistSong(Long playlistId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        PlaylistResponse playlistResponse = new PlaylistResponse();

        if (playlist.isPresent()) {
            playlistResponse.setId(playlist.get().getId());
            playlistResponse.setPlaylistName(playlist.get().getPlaylistName());
            playlistResponse.setSongList(playlist.get().getSongList());
            playlistResponse.setUserName(playlist.get().getUser().getUserName());
        }
        return playlistResponse;
    }


    @Override
    public List<PlaylistResponse> findAllByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> currentUser = userRepository.findUserByUserName(authentication.getName());

        List<Playlist> playlists = playlistRepository.findAllByUser(currentUser.orElseThrow(() -> new RuntimeException("User not found")));

        List<PlaylistResponse> playlistsResponse = new ArrayList<>();

        playlists.forEach(item -> {
            PlaylistResponse newPlaylist = new PlaylistResponse();

            newPlaylist.setId(item.getId());
            newPlaylist.setUserName(authentication.getName());
            newPlaylist.setPlaylistName(item.getPlaylistName());

            playlistsResponse.add(newPlaylist);
        });

        return playlistsResponse;
    }

    @Override
    public Page<PlaylistResponse> findPlaylistsByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        List<Playlist> playlists = playlistRepository.findAllByPlaylistNameContaining(title);

        List<PlaylistResponse> playlistResponse = new ArrayList<>();

        playlists.forEach(item -> {
            PlaylistResponse newPlaylist = new PlaylistResponse();

            newPlaylist.setId(item.getId());
            newPlaylist.setUserName(item.getUser().getUserName());
            newPlaylist.setPlaylistName(item.getPlaylistName());

            playlistResponse.add(newPlaylist);
        });

        return new PageImpl<>(playlistResponse, pageable, playlistResponse.size());
    }
}
