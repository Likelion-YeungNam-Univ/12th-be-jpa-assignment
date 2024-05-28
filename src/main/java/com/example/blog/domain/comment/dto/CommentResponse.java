package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.user.domain.User;

public record CommentResponse(
        String content,
        User user
) {
    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(comment.getContent(), comment.getUser());
    }
}
