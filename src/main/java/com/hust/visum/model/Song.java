package com.hust.visum.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String songName;

    private double duration;

    @Column(length = 500000)
    private byte[] image;

    private String imageName;

    @ColumnDefault(value = "0")
    private int views;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "playlist_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private Set<Playlist> playlists;


    @OneToMany(mappedBy = "song",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trending> trending;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    private Singer singer;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "singer_songs",
//            joinColumns = @JoinColumn(name = "song_id"),
//            inverseJoinColumns = @JoinColumn(name = "singer_id")
//    )
//    private Set<Singer> singers;

    @ManyToOne
    @JoinColumn(name = "composer_id")
    private Composer composer;

    @ManyToOne
    @JoinColumn(name = "subCategory_id")
    private SubCategory subCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "song")
    private List<Favorite> favorites;

    @JsonIgnore
    @OneToMany(mappedBy = "song")
    private List<Comment> comments;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "album_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private List<Album> albums;
}
