package com.example.blog.domain.post.domain;

import com.example.blog.global.enums.PostStatus;
import com.example.blog.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostStatus status;

    // 빌더 패턴
    @Builder
    public Post(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    // 편의 메서드
    public void modifyPost(String title,String content) {
        this.title = title;
        this.content = content;
    }

}
