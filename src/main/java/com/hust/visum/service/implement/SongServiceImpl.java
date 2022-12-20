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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Page<Song> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(!sortBy.isEmpty() ? sortBy : "createdAt"));
        return songRepository.findAll(pageable);
    }

    @Override
    public Song createSong(SongDTO songDTO) {
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

    @Override
    public List<Song> createListSong(List<SongDTO> songDTOS) {
        return null;
    }

    @Override
    public Song updateSong(SongDTO songDTO, Long id) {
        Optional<Song> song = getSongById(id);

        if (song.isPresent()) {
            Song updateSong = song.get();

            updateSong.setSongName(songDTO.getSongName());
            updateSong.setDuration(songDTO.getDuration());
            updateSong.setImage(songDTO.getImage());
            updateSong.setImageName(songDTO.getImageName());

            if (songDTO.getComposer_id() != null) {
                Composer composer = composerRepository
                        .findById(songDTO.getComposer_id())
                        .orElse(null);

                updateSong.setComposer(composer);
            }

            if (songDTO.getSinger_id() != null) {
                Singer singer = singerRepository
                        .findById(songDTO.getSinger_id())
                        .orElse(null);

                updateSong.setSinger(singer);
            }

            if (songDTO.getSubCategory_id() != null) {
                SubCategory subCategory = subCategoryRepository
                        .findById(songDTO.getSubCategory_id())
                        .orElse(null);

                updateSong.setSubCategory(subCategory);
            }
            return songRepository.save(updateSong);
        }
        return null;
    }

    @Override
    public Optional<Song> getSongById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Page<Song> findSongsByTitle(String title, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return songRepository.findAllBySongName(title, pageable);
    }

    @Override
    public Page<Song> findSongsByCategory(String subCategoryName, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        SubCategory subCategory = subCategoryRepository
                .findSubCategoriesBySubCategoryName(subCategoryName)
                .orElse(null);

        return songRepository.findAllBySubCategory(
                subCategory,
                pageable
        );
    }

    @Override
    public Set<Song> findTop4BySinger(Long singerId) {
        return songRepository.findTop4BySinger_Id(singerId);
    }

    @Override
    public Set<Song> findTop4ByCategory(Long cateId) {
        return songRepository.findTop4BySubCategory_Id(cateId);
    }

    @Override
    public Page<Song> searchBySingerName(int page, int size, String sortBy, String singerName) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return songRepository.findAllBySinger_SingerName(pageable, singerName);
    }

    @Override
    public Page<Song> searchByComposerName(int page, int size, String sortBy, String composerName) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return songRepository.findAllByComposer_ComposerName(pageable, composerName);
    }

    @Override
    public Page<Song> findMostPopularSong(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return songRepository.findSongsByMostViews(pageable);
    }
}
