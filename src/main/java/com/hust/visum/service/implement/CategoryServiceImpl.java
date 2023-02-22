package com.hust.visum.service.implement;

import com.hust.visum.model.Category;
import com.hust.visum.model.Song;
import com.hust.visum.repository.CategoryRepository;
import com.hust.visum.repository.SongRepository;
import com.hust.visum.repository.SubCategoryRepository;
import com.hust.visum.request.CategoryDTO;
import com.hust.visum.response.SongByCategoryResponse;
import com.hust.visum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SongRepository songRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public List<SongByCategoryResponse> findAllSongsByCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<SongByCategoryResponse> songByCategoryResponses = new ArrayList<>();
        categories.forEach(item -> {
            List<Song> songs = songRepository.findSongByCategory(item.getId());
            SongByCategoryResponse song = new SongByCategoryResponse();

            song.setCategory(item);
            song.setSongs(songs);

            songByCategoryResponses.add(song);
        });
        return songByCategoryResponses;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
