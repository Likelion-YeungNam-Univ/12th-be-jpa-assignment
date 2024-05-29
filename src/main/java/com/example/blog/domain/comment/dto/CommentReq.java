package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;

public record CommentReq(
        Long userId,
        String content
) {
    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
