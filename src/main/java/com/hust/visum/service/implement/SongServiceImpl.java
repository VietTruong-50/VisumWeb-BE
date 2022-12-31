package com.hust.visum.service.implement;

import com.hust.visum.model.*;
import com.hust.visum.repository.*;
import com.hust.visum.request.CommentDTO;
import com.hust.visum.request.SongDTO;
import com.hust.visum.service.CommentService;
import com.hust.visum.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SongServiceImpl implements SongService, CommentService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ComposerRepository composerRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

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
    public Page<Song> findSongsByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return songRepository.findAllBySongNameContaining(title, pageable);
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
    public Page<Song> searchBySingerName(int page, int size, String singerName) {
        Pageable pageable = PageRequest.of(page, size);
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

    @Override
    public Comment createComment(CommentDTO commentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> user = userRepository.findUserByUserName(authentication.getName());
        Comment comment = new Comment();

        if (user.isPresent()) {
            comment.setContent(commentDTO.getCommentText());
            comment.setSong(songRepository.findById(commentDTO.getSongId()).orElse(null));
            comment.setUser(user.get());
            comment.setCreatedAt(LocalDateTime.now().toString());

            return commentRepository.save(comment);
        }

        return null;
    }

    @Override
    public Comment deleteComment(Long id) {
        var entity = this.findByCommentId(id);
        this.commentRepository.deleteById(entity.getId());

        return entity;
    }

    @Override
    public Comment findByCommentId(Long id) {
        var entity = this.commentRepository.findById(id);
        return entity.orElse(null);
    }

    @Override
    public Page<CommentDTO> getCommentPagination(Long filmId, int page, int size, String sortBy, String orderBy) {
        if (!Objects.equals(orderBy, "ASC") && !Objects.equals(orderBy, "DSC")) {
            throw new RuntimeException("INVALID_ORDER_BY_METHOD");
        }
        var song = this.songRepository.findById(filmId).orElse(null);

        List<CommentDTO> commentDTOs = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));


        if (song != null) {
            switch (orderBy) {
                case "ASC" -> song.getComments().sort(Comparator.comparing(Comment::getCreatedAt));
                case "DSC" -> song.getComments().sort(Comparator.comparing(Comment::getCreatedAt).reversed());
                default -> {

                }
            }

            song.getComments().forEach(s -> {
                CommentDTO commentDTO = new CommentDTO();

                commentDTO.setId(s.getId());
                commentDTO.setCommentText(s.getContent());
                commentDTO.setSongId(s.getSong().getId());
                commentDTO.setUserId(s.getUser().getId());
                commentDTO.setUserAvatar(s.getUser().getImageName());
                commentDTO.setUsername(s.getUser().getUserName());
                commentDTO.setCreatedAt(s.getCreatedAt());

                commentDTOs.add(commentDTO);
            });
        }

        return new PageImpl<>(commentDTOs, pageable, commentDTOs.size());
    }
}
