package com.hust.visum.repository;

import com.hust.visum.model.Favorite;
import com.hust.visum.model.Song;
import com.hust.visum.model.SubCategory;
import com.hust.visum.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song ,Long> {
    Page<Song> findAllBySubCategory(SubCategory subCategory, Pageable pageable);

    Page<Song> findAllBySongName(String songName, Pageable pageable);

    @Query("SELECT s FROM Song s " +
            "JOIN Favorite f ON s.id = f.song.id " +
            "WHERE f.user.userName = ?1")
    Page<Song> findFavoriteListByUser(Pageable pageable, String userName);

    Set<Song> findTop4BySinger_Id(Long singerId);

    Set<Song> findTop4BySubCategory_Id(Long cateId);

    Page<Song> findAllBySinger_SingerName(Pageable pageable, String singerName);

    Page<Song> findAllByComposer_ComposerName(Pageable pageable, String composerName);

}
