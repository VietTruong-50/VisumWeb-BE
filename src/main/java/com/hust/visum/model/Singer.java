package com.hust.visum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "singers")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String singerName;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "singer")
    private Set<Song> songList;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "singers")
//    private Set<Song> songList;
}
