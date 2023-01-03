package com.hust.visum.service.implement;

import com.hust.visum.model.Category;
import com.hust.visum.model.SubCategory;
import com.hust.visum.repository.CategoryRepository;
import com.hust.visum.repository.SubCategoryRepository;
import com.hust.visum.request.SubCategoryDTO;
import com.hust.visum.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public SubCategory createSubCategory(Long cateId, SubCategoryDTO subCategoryDTO) {
        Optional<Category> category = categoryRepository.findById(cateId);

        if (category.isPresent()) {
            SubCategory subCategory = new SubCategory();

            subCategory.setSubCategoryName(subCategoryDTO.getSubCategoryName());
            subCategory.setCategory(category.get());

            return subCategoryRepository.save(subCategory);
        }
        return null;
    }
}
