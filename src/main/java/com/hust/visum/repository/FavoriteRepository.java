package com.hust.visum.repository;

import com.hust.visum.model.Favorite;
import com.hust.visum.model.Song;
import com.hust.visum.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    void deleteBySongAndUser(Song song, User user);

    boolean existsBySong(Song song);
}
