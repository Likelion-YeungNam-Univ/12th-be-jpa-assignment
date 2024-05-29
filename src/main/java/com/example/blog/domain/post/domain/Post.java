package com.example.blog.domain.post.domain;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.user.domain.User;
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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private String title;
    private String content;
    private int viewCount = 0;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 편의 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 연관관계 편의 메서드
    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    public void incrementViewCount() {
        this.viewCount++;
    }
}