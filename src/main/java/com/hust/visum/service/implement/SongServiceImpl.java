package com.hust.visum.service.implement;

import com.hust.visum.model.Composer;
import com.hust.visum.model.Singer;
import com.hust.visum.model.Song;
import com.hust.visum.model.SubCategory;
import com.hust.visum.repository.ComposerRepository;
import com.hust.visum.repository.SingerRepository;
import com.hust.visum.repository.SongRepository;
import com.hust.visum.repository.SubCategoryRepository;
import com.hust.visum.request.SongDTO;
import com.hust.visum.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ComposerRepository composerRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public Song createNewSong(SongDTO songDTO) {
        Song song = new Song();

        if (songDTO != null) {
            song.setSongName(songDTO.getSongName());
            song.setImage(songDTO.getImage());
            song.setImageName(songDTO.getImageName());
            song.setDuration(songDTO.getDuration());

            if (songDTO.getComposer_id() != null) {
                Composer composer = composerRepository
                        .findById(songDTO.getComposer_id())
                        .orElse(null);

                song.setComposer(composer);
            }

            if (songDTO.getSinger_id() != null) {
                Singer singer = singerRepository
                        .findById(songDTO.getSinger_id())
                        .orElse(null);

                song.setSinger(singer);
            }

            if (songDTO.getSubCategory_id() != null) {
                SubCategory subCategory = subCategoryRepository
                        .findById(songDTO.getSubCategory_id())
                        .orElse(null);

                song.setSubCategory(subCategory);
            }
        }

        return songRepository.save(song);
    }
}
