package com.hust.visum.service;

import com.hust.visum.model.Album;
import com.hust.visum.model.Song;
import com.hust.visum.request.AlbumDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlbumService {

    Album createNewAlbum(AlbumDTO albumDTO);

    Song addSongToAlbum(Long albumId, Long songId);

    Song deleteSongInAlbum(Long albumId, Long songId);

    Album deleteAlbum(Long albumId);

    Album editAlbum();

    Page<Album> getAlbumsBySinger(Long singerId, int page, int size, String sortBy);

    Page<Album> getAllAlbum(int page, int size, String sortBy);

    Album findById(Long albumId);
}
