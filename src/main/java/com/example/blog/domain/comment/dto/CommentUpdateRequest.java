package com.example.blog.domain.comment.dto;

public record CommentUpdateRequest(
        Long userId,
        String content
) {
}
