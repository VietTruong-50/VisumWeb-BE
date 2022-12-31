package com.hust.visum.controller;

import com.hust.visum.model.Singer;
import com.hust.visum.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visum")
@CrossOrigin(origins = "*")
public class AlbumController {
//    @GetMapping(value = "/searchSingers", produces = "application/json")
//    public ApiResponse<Page<Album>> findSingersByTitle(@RequestParam String title,
//                                                        @RequestParam int page,
//                                                        @RequestParam int size,
//                                                        @RequestParam String sortBy) {
//        Page<Singer> singerList = singerService.findAllByName(title, page, size, sortBy);
//        return ApiResponse.successWithResult(singerList);
//    }
}
