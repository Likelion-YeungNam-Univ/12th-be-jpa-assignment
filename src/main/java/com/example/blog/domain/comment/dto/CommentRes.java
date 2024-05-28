package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;
import lombok.Getter;

@Getter
public class CommentRes {

    private final Long id;
    private final String content;
    private final String postTitle;
    private final String username;

    public CommentRes(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.postTitle = comment.getPost().getTitle();
        this.username = comment.getUser().getUsername();
    }
}
