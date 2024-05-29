package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;

public record CommentRequest(
        String content,
        Long userId
) {

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
