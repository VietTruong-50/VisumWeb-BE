package com.hust.visum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity()
@Table(name = "recently")
public class Recently {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long songId;

    private Date time;

    public Recently(User user, Long songId, Date time) {
        this.user = user;
        this.songId = songId;
        this.time = time;
    }
}
