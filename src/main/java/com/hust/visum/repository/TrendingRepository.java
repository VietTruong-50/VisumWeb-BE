package com.hust.visum.repository;

import com.hust.visum.model.Song;
import com.hust.visum.model.Trending;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrendingRepository extends JpaRepository<Trending, Long> {
    Trending findByDayAndSong(int week, Song song);

    @Query(value = "select s.id FROM songs s JOIN trending td on td.song_id = s.id " +
            "where td.week = ?1 group by s.id order by sum(td.views_day) DESC LIMIT 3 ", nativeQuery = true)
    List<Integer> getTop3SongByTotalViewInWeek(int week);

    @Query(value = "SELECT td.views_day FROM songs s" +
            " JOIN trending td ON td.song_id = s.id WHERE td.week = ?1 AND s.id = ?2 ORDER BY td.day ASC LIMIT 7", nativeQuery = true)
    List<Integer> getTotalSongViewsByDayAndWeek(int week, int songId);

    @Query(value = "select s FROM Song as s " +
            "JOIN Trending as td on td.song.id = s.id where td.week = ?1 group by s.id order by sum(td.viewsDay) DESC")
    List<Song> getSongChartList(int week);
}
