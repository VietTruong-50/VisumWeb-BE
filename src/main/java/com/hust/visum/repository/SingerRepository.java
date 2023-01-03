package com.hust.visum.repository;

import com.hust.visum.model.Composer;
import com.hust.visum.model.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerRepository  extends JpaRepository<Singer,Long> {
    Page<Singer> findAllBySingerNameContaining(String singerName, Pageable pageable);

    @Query(value = "SELECT * FROM singers " +
            "join songs on songs.singer_id = singers.id " +
            "order by songs.views desc limit 3", nativeQuery = true)
    List<Singer> findTopSingers();

}
