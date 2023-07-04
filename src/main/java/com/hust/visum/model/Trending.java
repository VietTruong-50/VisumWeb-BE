package com.hust.visum.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "trending")
public class Trending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int week;

    private int day;

    private int viewsDay;
    @ManyToOne()
    @JoinColumn(name = "song_id")
    private Song song;
}
