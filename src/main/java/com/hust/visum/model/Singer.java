package com.hust.visum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "singers")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String singerName;

    private String description;

    @Column(columnDefinition = "integer default 0")
    private Long followers;

    @JsonIgnore
    @OneToMany(mappedBy = "singer")
    private List<Song> songList;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "singers")
//    private Set<Song> songList;
}
