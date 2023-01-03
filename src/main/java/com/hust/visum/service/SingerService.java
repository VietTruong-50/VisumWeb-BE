package com.hust.visum.service;

import com.hust.visum.model.Singer;
import com.hust.visum.request.SingerDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SingerService {
    SingerDTO getSinger(Long singerId);

    Page<Singer> findAllByName(String singerName, int page, int size);

    List<Singer> findTopArtists();
}
