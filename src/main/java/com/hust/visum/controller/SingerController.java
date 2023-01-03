package com.hust.visum.controller;

import com.hust.visum.model.Singer;
import com.hust.visum.request.SingerDTO;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.service.implement.SingerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visum")
@CrossOrigin(origins = "*")
public class SingerController {

    @Autowired
    private SingerServiceImpl singerService;

    @GetMapping(value = "topArtists", produces = "application/json")
    public ApiResponse<List<Singer>> getTopArtists(){
        return ApiResponse.successWithResult(singerService.findTopArtists());
    }

    @GetMapping(value = "/searchSingers", produces = "application/json")
    public ApiResponse<Page<Singer>> findSingersByTitle(@RequestParam String title,
                                                    @RequestParam int page,
                                                    @RequestParam int size) {
        Page<Singer> singerList = singerService.findAllByName(title, page, size);
        return ApiResponse.successWithResult(singerList);
    }

    @GetMapping(value = "/singer/{singerId}", produces = "application/json")
    public ApiResponse<SingerDTO> findSingerById(@PathVariable("singerId") Long singerId){
        return ApiResponse.successWithResult(singerService.getSinger(singerId));
    }

}
