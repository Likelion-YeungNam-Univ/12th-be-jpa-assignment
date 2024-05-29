package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;

public record CommentRes(
        Long id,
        String writer,
        String content,
        int like
) {
    public static CommentRes fromEntity(Comment comment) {
        return new CommentRes(
                comment.getId(),
                comment.getUser().getUsername(),
                comment.getContent(),
                comment.getLikes()
        );
    }
}
