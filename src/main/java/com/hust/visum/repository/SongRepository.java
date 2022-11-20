package com.hust.visum.repository;

import com.hust.visum.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song ,Long> {
}
