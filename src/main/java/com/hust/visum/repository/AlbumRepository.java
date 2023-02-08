package com.hust.visum.repository;

import com.hust.visum.model.Album;
import com.hust.visum.model.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Page<Album> findBySinger(Pageable pageable, Singer singer);
}
