package com.hust.visum.repository;

import com.hust.visum.model.Playlist;
import com.hust.visum.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    List<Playlist> findAllByUser(User user);

    List<Playlist> findAllByPlaylistNameContaining(String playlistName);

    Playlist findByUserAndId(User user, Long playlistId);

    @Modifying
    @Query(value = "DELETE FROM playlist_songs WHERE playlist_id = ?1", nativeQuery = true)
    void deleteAllSongInPlaylist(Long playlistId);
}
