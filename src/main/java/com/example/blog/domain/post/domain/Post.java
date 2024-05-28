package com.example.blog.domain.post.domain;

import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.views = 0;
    }

    // 편의 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void views(){
        this.views++;
    }

    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }
}