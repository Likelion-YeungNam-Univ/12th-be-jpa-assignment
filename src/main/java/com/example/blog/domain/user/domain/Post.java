package com.example.blog.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Timestamp postCreatedDate;

    @Column(nullable = false, length = 255)
    private String postContent;

    @Column(nullable = false, length = 255)
    private String postTitle;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long categoryId;

    // 빌더 패턴
    @Builder
    public Post(Timestamp postCreatedDate, String postContent, String postTitle, Long userId, Long categoryId) {
        this.postCreatedDate = postCreatedDate;
        this.postContent = postContent;
        this.postTitle = postTitle;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    // 편의 메서드
    public void updatePost(String postContent, String postTitle, Long categoryId) {
        if (postContent != null) {
            this.postContent = postContent;
        }
        if (postTitle != null) {
            this.postTitle = postTitle;
        }
        if (categoryId != null) {
            this.categoryId = categoryId;
        }
    }
}
