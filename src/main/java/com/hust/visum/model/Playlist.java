package com.hust.visum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playlistName;

    @ManyToMany(mappedBy = "playlists")
    private List<Song> songList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    public void addSong(Song song) {
//        this.songList.add(song);
//        song.getPlaylists().add(this);
//    }
//
//    public void removeSong(long songId) {
//        Song song = this.songList.stream().filter(t -> t.getId() == songId).findFirst().orElse(null);
//        if (song != null) {
//            this.songList.remove(song);
//            song.getPlaylists().remove(this);
//        }
//    }
}
