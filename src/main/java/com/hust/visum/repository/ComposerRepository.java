package com.hust.visum.repository;

import com.hust.visum.model.Composer;
import com.hust.visum.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposerRepository  extends JpaRepository<Composer,Long> {
    Page<Composer> findAllByComposerName(String composerName, Pageable pageable);

}
