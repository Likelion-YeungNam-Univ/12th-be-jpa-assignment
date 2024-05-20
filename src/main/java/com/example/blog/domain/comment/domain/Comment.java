package com.example.blog.domain.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    @Builder
    public Comment(String title, String content) {
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