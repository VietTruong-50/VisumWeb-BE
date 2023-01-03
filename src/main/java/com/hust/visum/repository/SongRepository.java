package com.hust.visum.repository;

import com.hust.visum.model.Song;
import com.hust.visum.model.SubCategory;
import com.hust.visum.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Page<Song> findAllBySubCategory(SubCategory subCategory, Pageable pageable);

    @Query(value = "select * from songs " +
            "join singers on songs.singer_id = singers.id " +
            "where songs.song_name like %:key% or singers.singer_name like %:key%", nativeQuery = true)
    Page<Song> findAllBySongNameContaining(@Param("key") String key, Pageable pageable);

    @Query(value = "SELECT s.* FROM songs s " +
            "JOIN favorites f ON s.id = f.song_id " +
            "JOIN users u ON f.user_id = u.id " +
            "WHERE u.user_name = ?1", nativeQuery = true)
    Page<Song> findFavoriteListByUser(Pageable pageable, String userName);

    @Query(value = "SELECT * FROM songs " +
            "WHERE id NOT IN (SELECT song_id FROM playlist_songs WHERE playlist_songs.playlist_id = ?1)", nativeQuery = true)
    Page<Song> findSongNotInPlaylist(Long playlistId, Pageable pageable);

    Set<Song> findTop4BySinger_Id(Long singerId);

    Set<Song> findTop4BySubCategory_Id(Long cateId);

    Page<Song> findAllBySinger_SingerName(Pageable pageable, String singerName);

    Page<Song> findAllByComposer_ComposerName(Pageable pageable, String composerName);

    @Query(value = "SELECT * FROM songs ORDER BY views DESC", nativeQuery = true)
    Page<Song> findSongsByMostViews(Pageable pageable);

}
