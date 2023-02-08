package com.hust.visum.service;

import com.hust.visum.model.Category;
import com.hust.visum.request.CategoryDTO;
import com.hust.visum.response.SongByCategoryResponse;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    List<SongByCategoryResponse> findAllSongsByCategory();

    List<Category> findAll();
}
