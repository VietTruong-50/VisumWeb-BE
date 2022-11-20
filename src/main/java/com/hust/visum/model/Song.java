package com.hust.visum.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String songName;

    private int duration;

    @Column(length = 500000)
    private byte[] image;

    private String imageName;

    @ColumnDefault(value = "0")
    private int views;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "playlist_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private Set<Playlist> playlists;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "trending_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "trending_id")
    )
    private Set<Trending> trending;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    private Singer singer;

    @ManyToOne
    @JoinColumn(name = "composer_id")
    private Composer composer;

    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory;
}
