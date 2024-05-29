package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.post.domain.Post;

public record CommentRes (
        Long id,
        Long userId,
        Long postId,
        String content

){

    public CommentRes fromEntity(Comment comment) {
        return new CommentRes(comment.getId(), userId, postId, comment.getContent());
    }
}