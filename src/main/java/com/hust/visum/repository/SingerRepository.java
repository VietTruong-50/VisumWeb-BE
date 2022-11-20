package com.hust.visum.repository;

import com.hust.visum.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository  extends JpaRepository<Singer,Long> {
}
