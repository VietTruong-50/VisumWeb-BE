package com.hust.visum.repository;

import com.hust.visum.model.Composer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposerRepository  extends JpaRepository<Composer,Long> {
}
