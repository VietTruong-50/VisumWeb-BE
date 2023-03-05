package com.hust.visum.repository;

import com.hust.visum.model.Recently;
import com.hust.visum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentLyRepository extends JpaRepository<Recently, Long> {
    List<Recently> findAllByUser(User user);

    boolean existsByUserAndSongId(User user, Long songId);
}
