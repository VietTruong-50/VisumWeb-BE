package com.hust.visum.request;

import com.hust.visum.model.Song;
import lombok.Data;

import java.util.List;

@Data
public class SingerDTO {
    private String singerName;

    private String description;

    private List<Song> songList;
}
