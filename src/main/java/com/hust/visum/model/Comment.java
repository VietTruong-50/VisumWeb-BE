//package com.hust.visum.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "comments")
//public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "song_id")
//    private Song song;
//
//    private String content;
//}
