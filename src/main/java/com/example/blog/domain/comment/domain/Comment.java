package com.example.blog.domain.comment.domain;

import com.example.blog.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    // 빌더 패턴
    @Builder
    public Comment(String content) {
        this.content = content;
    }

    // 편의 메서드
    public void modifyContent(String content) {
        this.content = content;
    }

}
