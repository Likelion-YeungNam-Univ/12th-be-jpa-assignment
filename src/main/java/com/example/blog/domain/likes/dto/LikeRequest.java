package com.example.blog.domain.likes.dto;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.likes.domain.Likes;
import com.example.blog.domain.user.domain.User;

public record LikeRequest(
        Long userId
) {
    public Likes toEntity(User user, Comment comment){
        return Likes.builder()
                .user(user)
                .comment(comment)
                .build();
    }
}
