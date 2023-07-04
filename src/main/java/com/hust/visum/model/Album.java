package com.hust.visum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity()
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String imageName;

    private byte[] imageByte;
    @ManyToOne()
    @JoinColumn(name = "singer_id")
    private Singer singer;

    @ManyToMany(mappedBy = "albums")
    private List<Song> songList;

}
