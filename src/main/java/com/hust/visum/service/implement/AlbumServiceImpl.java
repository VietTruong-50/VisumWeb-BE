package com.hust.visum.service.implement;

import com.hust.visum.model.Album;
import com.hust.visum.model.Singer;
import com.hust.visum.model.Song;
import com.hust.visum.repository.AlbumRepository;
import com.hust.visum.repository.SingerRepository;
import com.hust.visum.repository.SongRepository;
import com.hust.visum.request.AlbumDTO;
import com.hust.visum.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public Album createNewAlbum(AlbumDTO albumDTO) {
        return null;
    }

    @Override
    public Song addSongToAlbum(Long albumId, Long songId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Album not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Song not found"));

        album.getSongList().add(song);

        albumRepository.save(album);

        return song;
    }

    @Override
    public Song deleteSongInAlbum(Long albumId, Long songId) {
        return null;
    }

    @Override
    public Album deleteAlbum(Long albumId) {
        return null;
    }

    @Override
    public Album editAlbum() {
        return null;
    }

    @Override
    public Page<Album> getAlbumsBySinger(Long singerId, int page, int size, String sortBy) {
        Singer singer = singerRepository.findById(singerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Singer not found"));

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return albumRepository.findBySinger(pageable, singer);
    }

    @Override
    public Page<Album> getAllAlbum(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return albumRepository.findAll(pageable);
    }

    @Override
    public Album findById(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Album not found"));

        return album;
    }
}
