package com.hust.visum.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playlistName;

    @ManyToMany(mappedBy = "playlists")
    private Set<Song> songList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
