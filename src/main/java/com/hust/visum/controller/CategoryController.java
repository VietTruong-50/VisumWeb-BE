package com.hust.visum.controller;

import com.hust.visum.model.Category;
import com.hust.visum.response.ApiResponse;
import com.hust.visum.response.SongByCategoryResponse;
import com.hust.visum.service.implement.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/visum")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping(value = "/categories", produces = "application/json")
    public ApiResponse<List<Category>> getAllCategory(){
        return ApiResponse.successWithResult(categoryService.findAll());
    }

    @GetMapping(value = "/categories/songs", produces = "application/json")
    public ApiResponse<List<SongByCategoryResponse>> getAllSongByCategory(){
        return ApiResponse.successWithResult(categoryService.findAllSongsByCategory());
    }
}
