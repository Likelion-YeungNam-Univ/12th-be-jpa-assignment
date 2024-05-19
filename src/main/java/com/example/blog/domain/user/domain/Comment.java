package com.example.blog.domain.user.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자의 접근 제한
public class Comment {
    @Id
    private String commentId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false, length = 255)
    private String commentContent;

    // Builder 패턴
    @Builder
    public Comment(String commentId, Long userId, Long postId, String commentContent) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.commentContent = commentContent;
    }

    // 편의 메소드
    public void updateComment(String commentContent) {
        this.commentContent = commentContent;
    }
}
