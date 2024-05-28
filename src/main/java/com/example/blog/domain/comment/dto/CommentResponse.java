package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;

public record CommentResponse(
        String content
) {
    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(comment.getContent());
    }
}
