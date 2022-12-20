package com.hust.visum.service;

import com.hust.visum.model.Song;
import com.hust.visum.request.SongDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SongService {
    Page<Song> findAll(int page, int size, String sortBy);

    Song createSong(SongDTO songDTO);

    List<Song> createListSong(List<SongDTO> songDTOS);

    Song updateSong(SongDTO songDTO, Long id);

    Optional<Song> getSongById(Long id);

    void deleteSong(Long id);

    Page<Song> findSongsByTitle(String title, int page, int size, String sortBy);

    Page<Song> findSongsByCategory(String subCategory, int page, int size, String sortBy);

    Set<Song> findTop4BySinger(Long singerId);

    Set<Song> findTop4ByCategory(Long cateId);

    Page<Song> searchBySingerName(int page, int size, String sortBy, String singerName);

    Page<Song> searchByComposerName(int page, int size, String sortBy, String composerName);

    Page<Song> findMostPopularSong(int page, int size, String sortBy);
}
