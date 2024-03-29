package com.hust.visum.controller;

import com.hust.visum.model.Song;
import com.hust.visum.model.Trending;
import com.hust.visum.request.SongDTO;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.ChartResponse;
import com.hust.visum.service.implement.SongServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/visum")
@CrossOrigin(origins = "*")
public class SongController {

    @Autowired
    private SongServiceImpl songServiceImpl;

    @PostMapping(value = "/insertSong", produces = "application/json")
    public ApiResponse<Song> createSong(@RequestBody @Valid SongDTO songDTO) {
        Song song = songServiceImpl.createSong(songDTO);
        return ApiResponse.successWithResult(song);
    }

    @PostMapping(value = "/insertListSong", produces = "application/json")
    public ApiResponse<List<Song>> createListSong(@RequestBody @Valid List<SongDTO> songDTO) {
        List<Song> songs = songServiceImpl.createListSong(songDTO);
        return ApiResponse.successWithResult(songs);
    }

    @GetMapping(value = "/songs", produces = "application/json")
    public ApiResponse<Page<Song>> getSong(@RequestParam int page,
                                           @RequestParam int size,
                                           @RequestParam String sortBy) {
        Page<Song> songList = songServiceImpl.findAll(page, size, sortBy);
        return ApiResponse.successWithResult(songList);
    }

    @GetMapping(value = "/songs/{songId}", produces = "application/json")
    public ApiResponse<Optional<Song>> findSongById(@PathVariable("songId") Long songId) {
        Optional<Song> song = songServiceImpl.getSongById(songId);
        return ApiResponse.successWithResult(song);
    }

    @GetMapping(value = "/searchSongs", produces = "application/json")
    public ApiResponse<Page<Song>> findSongsByTitle(@RequestParam String title,
                                                    @RequestParam int page,
                                                    @RequestParam int size) {
        Page<Song> songList = songServiceImpl.findSongsByTitle(title, page, size);
        return ApiResponse.successWithResult(songList);
    }

    @GetMapping(value = "/songs/category", produces = "application/json")
    public ApiResponse<Page<Song>> findSongsByCategory(@RequestParam String subCategoryName,
                                                    @RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam String sortBy) {
        Page<Song> songList = songServiceImpl.findSongsByCategory(subCategoryName, page, size, sortBy);
        return ApiResponse.successWithResult(songList);
    }

    @PutMapping(value = "/songs/{songId}", produces = "application/json")
    public ApiResponse<Song> updateSong(@PathVariable("songId") Long id, @RequestBody SongDTO songDTO) {
        Song song = songServiceImpl.updateSong(songDTO, id);
        return ApiResponse.successWithResult(song);
    }

    @DeleteMapping(value = "/songs/{songId}", produces = "application/json")
    public ApiResponse<Song> deleteSong(@PathVariable("songId") Long id) {
        songServiceImpl.deleteSong(id);
        return ApiResponse.successWithResult(null, "Delete success");
    }

    @PostMapping(value = "song/{songId}/view", produces = "application/json")
    public ApiResponse<Trending> increaseSongView(@PathVariable("songId") Long songId){
        return ApiResponse.successWithResult(songServiceImpl.updateSongViews(songId));
    }

    @GetMapping(value = "song/chart", produces = "application/json")
    public ApiResponse<ChartResponse> getSongChart(@RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam String sortBy){
        return ApiResponse.successWithResult(songServiceImpl.getSongChart(page, size, sortBy));
    }

    @GetMapping(value = "recently", produces = "application/json")
    public ApiResponse<List<Song>> getRecentlySongs(){
        return ApiResponse.successWithResult(songServiceImpl.getRecentlySongs());
    }
}
