package com.hust.visum.service;

import com.hust.visum.model.Composer;
import org.springframework.data.domain.Page;

public interface ComposerService {
    Page<Composer> findAll(int page, int size, String sortBy);

    Composer findById(Long id);

    Page<Composer> findAllByName(String name, int page, int size, String sortBy);
}
