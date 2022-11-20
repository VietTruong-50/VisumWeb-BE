package com.hust.visum.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "composers")
public class Composer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String composerName;

    private String information;

    @OneToMany(mappedBy = "composer")
    private Set<Song> songList;
}
