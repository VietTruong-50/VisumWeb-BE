package com.hust.visum.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "trending")
public class Trending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int views;

    private int week;

    private int month;

    @ManyToMany(mappedBy = "trending")
    private Set<Song> songs;
}
