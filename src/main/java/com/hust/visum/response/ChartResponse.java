package com.hust.visum.response;

import com.hust.visum.model.Song;
import lombok.Data;

import java.util.List;

@Data
public class ChartResponse {
    List<Integer> top1;

    List<Integer> top2;

    List<Integer> top3;

    List<Song> songs;
}
