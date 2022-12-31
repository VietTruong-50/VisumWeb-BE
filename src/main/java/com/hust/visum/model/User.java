package com.hust.visum.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hust.visum.Enum.GenderEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
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

    private GenderEnum genderEnum;

    private String email;

    private String mobile;

    private String status;

    @OneToMany
    private Set<Playlist> playlists;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;

    public User(String username, String password, String email) {
        this.userName = username;
        this.password = password;
        this.email = email;
    }
}
