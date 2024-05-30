package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;
import lombok.Getter;

@Getter
public class CommentRes {
    private Long id;
    private String content;
    private String username;
    private Long postId;

    public CommentRes(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getUser().getUsername();
        this.postId = comment.getPost().getId();
    }

    public static CommentRes fromEntity(Comment comment) {
        return new CommentRes(comment);
    }
}
