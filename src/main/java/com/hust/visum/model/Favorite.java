package com.hust.visum.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int userId;

    private int songId;
}
