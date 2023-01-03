package com.hust.visum.response;

import com.hust.visum.model.Song;
import lombok.Data;

import java.util.List;

@Data
public class PlaylistResponse {

    private Long id;

    private String playlistName;

    private String userName;

    private List<Song> songList;
}
