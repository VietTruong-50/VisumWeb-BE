package com.hust.visum.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "singers")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String singerName;

    private String description;

    @OneToMany(mappedBy = "singer")
    private List<Song> songList;
}
