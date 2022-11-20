package com.hust.visum.model;

import com.hust.visum.Enum.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    @Column(length = 500000)
    private byte[] avatarImage;

    private String imageName;

    private Date birthOfDate;

    private Gender gender;

    private String email;

    private String mobile;

    private String status;

    @OneToMany
    private Set<Playlist> playlists;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User(String username, String password, String email) {
        this.userName = username;
        this.password = password;
        this.email = email;
    }
}
