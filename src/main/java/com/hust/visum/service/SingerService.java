package com.hust.visum.service;

import com.hust.visum.model.Singer;
import org.springframework.data.domain.Page;

public interface SingerService {
    Singer getSinger(Long singerId);

    Page<Singer> findAllByName(String singerName, int page, int size, String sortBy);
}
