package com.hust.visum.service.implement;

import com.hust.visum.model.Playlist;
import com.hust.visum.model.Song;
import com.hust.visum.model.SubCategory;
import com.hust.visum.model.User;
import com.hust.visum.repository.PlaylistRepository;
import com.hust.visum.repository.SongRepository;
import com.hust.visum.repository.UserRepository;
import com.hust.visum.request.PlaylistDTO;
import com.hust.visum.response.PlaylistResponse;
import com.hust.visum.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        playlistRepository.findById(playlistId).ifPresent(playlist -> {
            playlist.setPlaylistName(playlistDTO.getPlaylistName());
            playlistRepository.save(playlist);
        });
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
    public void removeSongFromPlaylist(Long playListId, Long songId) {
        playlistRepository.findById(playListId).ifPresent(playlist -> {
            Song song = songRepository.findById(songId).orElse(null);
            if (song != null) {

                playlist.getSongList().remove(song);

                song.getPlaylists().remove(playlist);

                songRepository.save(song);
            }
        });
    }


    @Override
    public PlaylistResponse getPlaylistSong(Long playlistId, String orderBy, String sortType) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        PlaylistResponse playlistResponse = new PlaylistResponse();

        if (playlist.isPresent()) {
            if (Objects.equals(sortType, "name")) {
                switch (orderBy) {
                    case "ASC" -> playlist.get().getSongList().sort(Comparator.comparing(Song::getSongName));
                    case "DSC" -> playlist.get().getSongList().sort(Comparator.comparing(Song::getSongName).reversed());
                    default -> {

                    }
                }
            } else if (Objects.equals(sortType, "duration")) {
                switch (orderBy) {
                    case "ASC" -> playlist.get().getSongList().sort(Comparator.comparing(Song::getDuration));
                    case "DSC" -> playlist.get().getSongList().sort(Comparator.comparing(Song::getDuration).reversed());
                    default -> {

                    }
                }
            }

            playlistResponse.setId(playlist.get().getId());
            playlistResponse.setPlaylistName(playlist.get().getPlaylistName());
            playlistResponse.setSongList(playlist.get().getSongList());
            playlistResponse.setUserName(playlist.get().getUser().getUserName());
        }
        return playlistResponse;
    }

    @Override
    public Page<Song> recommendSongNotInPlaylist(Long playlistId, int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> currentUser = userRepository.findUserByUserName(authentication.getName());

        List<Song> finalSongs = new ArrayList<>();

        if (currentUser.isPresent()) {
            Map<SubCategory, Long> subCategoryMap = new HashMap<>();

            Playlist playlist = playlistRepository.findByUserAndId(currentUser.get(), playlistId);

            List<Song> songs = playlist.getSongList();

            if (!songs.isEmpty()) {
                songs.forEach(item -> {
                    var subcategory = item.getSubCategory();

                    subCategoryMap.put(subcategory, subCategoryMap.containsKey(subcategory) ? subCategoryMap.get(subcategory) + 1 : 1L);
                });

                Map<SubCategory, Long> finalSubCategoryMap = subCategoryMap.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(3)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

                finalSubCategoryMap.forEach((key, value) -> finalSongs.addAll(songRepository.findSongSameSubCategoryNotInPlaylist(playlistId, key.getId())));
            }
        }
        Pageable pageable = PageRequest.of(page, size);

        if (!finalSongs.isEmpty()) {
            Collections.shuffle(finalSongs);
            return new PageImpl<>(finalSongs, pageable, finalSongs.size());
        } else {
            return new PageImpl<>(songRepository.findAllSongNotInPlaylist(playlistId), pageable, songRepository.findAllSongNotInPlaylist(playlistId).size());
        }
    }


    @Override
    public List<PlaylistResponse> findAllByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> currentUser = userRepository.findUserByUserName(authentication.getName());

        List<Playlist> playlists = playlistRepository.findAllByUser(currentUser.orElseThrow(() -> new RuntimeException("Playlist not found")));

        List<PlaylistResponse> playlistsResponse = new ArrayList<>();

        playlists.forEach(item -> {
            PlaylistResponse newPlaylist = new PlaylistResponse();

            newPlaylist.setId(item.getId());
            newPlaylist.setUserName(authentication.getName());
            newPlaylist.setPlaylistName(item.getPlaylistName());
            newPlaylist.setSongList(item.getSongList());

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
            newPlaylist.setSongList(item.getSongList());

            playlistResponse.add(newPlaylist);
        });

        return new PageImpl<>(playlistResponse, pageable, playlistResponse.size());
    }
}
