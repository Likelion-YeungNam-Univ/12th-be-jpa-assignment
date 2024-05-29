package com.example.blog.domain.user.domain;

import com.example.blog.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public User (String userId, String username, String phone, String password) {

        this.userId = userId;
        this.username = username;
        this.phone = phone;
        this.password = password;

    }

    //편의 메서드
    public void update(String userId, String username, String phone, String password) {
        this.userId = userId;
        this.username = username;
        this.password = phone;
        this.phone = password;
    }


}