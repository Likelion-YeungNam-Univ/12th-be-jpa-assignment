package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.doamin.Comment;

public record CommentListResponse(
        Long commentId,
        Long userId,
        String content,
        int likeCount
) {
    public static CommentListResponse fromEntity(Comment comment){
        return new CommentListResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getContent(),
                comment.getLikes().size()
        );
    }
}
