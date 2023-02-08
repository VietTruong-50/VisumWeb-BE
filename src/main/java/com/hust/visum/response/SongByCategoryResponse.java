package com.hust.visum.response;

import com.hust.visum.model.Category;
import com.hust.visum.model.Song;
import com.hust.visum.model.SubCategory;
import lombok.Data;

import java.util.List;

@Data
public class SongByCategoryResponse {
    private Category category;

    private List<Song> songs;

}
