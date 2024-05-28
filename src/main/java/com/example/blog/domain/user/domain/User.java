package com.example.blog.domain.user.domain;

import com.example.blog.domain.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성
    private Long id;

    @Column(nullable = true)
    private String username;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    // 빌더 패턴
    @Builder
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    //편의 메서드
    public void update(String username, String password) {
        this.username = username;
        this.password = password;
    }
}