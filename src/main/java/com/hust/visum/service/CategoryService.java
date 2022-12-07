package com.hust.visum.service;

import com.hust.visum.model.Category;
import com.hust.visum.request.CategoryDTO;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    List<Category> findAll();
}
