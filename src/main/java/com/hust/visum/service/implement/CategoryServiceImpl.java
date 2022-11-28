package com.hust.visum.service.implement;

import com.hust.visum.model.Category;
import com.hust.visum.repository.CategoryRepository;
import com.hust.visum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
