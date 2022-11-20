package com.hust.visum.service;

import com.hust.visum.model.Song;
import com.hust.visum.request.SongDTO;

public interface SongService {
    Song createNewSong(SongDTO songDTO);
}
