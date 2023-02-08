package com.hust.visum.controller;

import com.hust.visum.model.Album;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.service.implement.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visum")
@CrossOrigin(origins = "*")
public class AlbumController {

    @Autowired
    private AlbumServiceImpl albumService;

    @GetMapping(value = "/album/singer/{singerId}", produces = "application/json")
    public ApiResponse<Page<Album>> getAllAlbumBySinger(@PathVariable("singerId") Long singerId,
                                                        @RequestParam int page,
                                                        @RequestParam int size,
                                                        @RequestParam String sortBy) {
        Page<Album> albums = albumService.getAlbumsBySinger(singerId, page, size, sortBy);
        return ApiResponse.successWithResult(albums);
    }

    @GetMapping(value = "/albums", produces = "application/json")
    public ApiResponse<Page<Album>> getAllAlbum(@RequestParam int page,
                                                @RequestParam int size,
                                                @RequestParam String sortBy) {
        Page<Album> albums = albumService.getAllAlbum(page, size, sortBy);
        return ApiResponse.successWithResult(albums);
    }

    @GetMapping(value = "/albums/{albumId}", produces = "application/json")
    public ApiResponse<Album> getAlbumById(@PathVariable("albumId") Long albumId) {
        Album album = albumService.findById(albumId);
        return ApiResponse.successWithResult(album);
    }
}
