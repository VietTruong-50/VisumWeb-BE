package com.hust.visum.service.implement;

import com.hust.visum.model.Singer;
import com.hust.visum.repository.SingerRepository;
import com.hust.visum.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public Singer getSinger(Long singerId) {
        return singerRepository.findById(singerId).orElse(null);
    }

    @Override
    public Page<Singer> findAllByName(String singerName, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return singerRepository.findAllBySingerName(singerName, pageable);
    }
}
