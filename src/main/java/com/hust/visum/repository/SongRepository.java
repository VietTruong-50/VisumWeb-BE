package com.hust.visum.repository;

import com.hust.visum.model.Song;
import com.hust.visum.model.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Page<Song> findAllBySubCategory(SubCategory subCategory, Pageable pageable);

    @Query(value = "SELECT * FROM songs " +
            "JOIN sub_categories on songs.sub_category_id = sub_categories.id " +
            "JOIN categories on categories.id = sub_categories.category_id where categories.id = ?1",nativeQuery = true)
    List<Song> findSongByCategory(Long cateId);

    @Query(value = "select * from songs " +
            "join singers on songs.singer_id = singers.id " +
            "where songs.song_name like %:key% or singers.singer_name like %:key%", nativeQuery = true)
    Page<Song> findAllBySongNameContaining(@Param("key") String key, Pageable pageable);

    @Query(value = "SELECT s FROM Song s JOIN Favorite f ON s.id = f.song.id " +
            "JOIN User u ON f.user.id = u.id WHERE u.userName = :userName")
    Page<Song> findFavoriteListByUser(Pageable pageable, String userName);

    @Query(value = "SELECT * FROM songs " +
            "JOIN sub_categories on songs.sub_category_id = sub_categories.id " +
            "WHERE songs.id NOT IN (SELECT song_id FROM playlist_songs WHERE playlist_songs.playlist_id = ?1) " +
            "AND sub_categories.id = ?2", nativeQuery = true)
    List<Song> findSongSameSubCategoryNotInPlaylist(Long playlistId, Long subCateId);

    @Query(value = "SELECT * FROM songs " +
            "JOIN sub_categories on songs.sub_category_id = sub_categories.id " +
            "WHERE songs.id NOT IN (SELECT song_id FROM playlist_songs WHERE playlist_songs.playlist_id = ?1)", nativeQuery = true)
    List<Song> findAllSongNotInPlaylist(Long playlistId);

    Set<Song> findTop4BySinger_Id(Long singerId);

    Set<Song> findTop4BySubCategory_Id(Long cateId);

    Page<Song> findAllBySinger_SingerName(Pageable pageable, String singerName);

    Page<Song> findAllByComposer_ComposerName(Pageable pageable, String composerName);

    @Query(value = "SELECT * FROM songs ORDER BY views DESC", nativeQuery = true)
    Page<Song> findSongsByMostViews(Pageable pageable);

}
