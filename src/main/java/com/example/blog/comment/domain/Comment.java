package com.example.blog.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    private Long commentId;

    //fk
    @Column(name = "postid", nullable = false)
    private Long postId;

    @Column(name = "userid", nullable = false)
    private Long userId;


    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "comment_date", nullable = false)
    private LocalDateTime commentDate;

    @Builder
    public Comment(Long postId, Long userId, String title, String content) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.commentDate = LocalDateTime.now();
    }

    //수정
    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateContent(String content) {
        this.content = content;
    }
    public void updateCommentInfo(String title, String content) {
        this.title=title;
        this.content=content;
    }
}
