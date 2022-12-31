package com.hust.visum.service.implement;

import com.hust.visum.model.Singer;
import com.hust.visum.repository.SingerRepository;
import com.hust.visum.request.SingerDTO;
import com.hust.visum.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public SingerDTO getSinger(Long singerId) {
        Optional<Singer> singer = singerRepository.findById(singerId);

        if(singer.isPresent()){
            SingerDTO singerDTO = new SingerDTO();

            singerDTO.setSingerName(singer.get().getSingerName());
            singerDTO.setDescription(singer.get().getDescription());
            singerDTO.setSongList(singer.get().getSongList());

            return singerDTO;
        }
        return null;
    }

    @Override
    public Page<Singer> findAllByName(String singerName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return singerRepository.findAllBySingerNameContaining(singerName, pageable);
    }

    @Override
    public List<Singer> findTopArtists() {
        return singerRepository.findTopSingers();
    }
}
