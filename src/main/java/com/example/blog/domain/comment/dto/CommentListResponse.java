package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.doamin.Comment;

public record CommentListResponse(
        Long userId,
        String content,
        int likeCount
) {
    public static CommentListResponse fromEntity(Comment comment){
        return new CommentListResponse(
                comment.getUser().getId(),
                comment.getContent(),
                comment.getLikeCount()
        );
    }
}
