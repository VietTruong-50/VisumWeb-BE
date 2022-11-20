package com.hust.visum.repository;

import com.hust.visum.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository  extends JpaRepository<SubCategory,Long> {
}
