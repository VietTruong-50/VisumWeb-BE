package com.hust.visum.request;

import com.hust.visum.model.SubCategory;
import lombok.Data;

import java.util.Set;

@Data
public class CategoryDTO {
    private String categoryName;

    private Set<SubCategory> subCategories;
}
