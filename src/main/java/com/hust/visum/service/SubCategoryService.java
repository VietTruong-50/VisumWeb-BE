package com.hust.visum.service;

import com.hust.visum.model.SubCategory;
import com.hust.visum.request.SubCategoryDTO;

public interface SubCategoryService {
    SubCategory createSubCategory(Long cateId, SubCategoryDTO subCategoryDTO);
}
