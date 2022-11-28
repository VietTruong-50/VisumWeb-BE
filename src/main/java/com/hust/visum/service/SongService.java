package com.hust.visum.service;

import com.hust.visum.model.Song;
import com.hust.visum.request.SongDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SongService {
    Page<Song> findAll(int page, int size, String sortBy);
    Song createSong(SongDTO songDTO);
    List<Song> createListSong(List<SongDTO> songDTOS);
    Song updateSong(SongDTO songDTO, String id);
    Optional<Song> getSongById(String uuid);
    void deleteSong(String uuid);
    Page<Song> findSongsByTitle(String title, int page, int size, String sortBy);
    Page<Song> findSongsByCategory(List<String> category, int page, int size, String sortBy);
}
