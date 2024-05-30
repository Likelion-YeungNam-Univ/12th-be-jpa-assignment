package com.example.blog.domain.post.domain;

import com.example.blog.domain.post.dto.PostRes;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserRes;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user; // 작성자 필드
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    // 조회수 기능 추가
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

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
}