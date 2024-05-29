package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;

public record CommentRequest (
        Long userId,
        Long postId,
        String content
){
    public Comment toEntity(User user, Post post){
        return Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .build();
    }
}
