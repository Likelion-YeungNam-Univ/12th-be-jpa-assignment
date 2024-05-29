package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;

public record PostRes (
    Long id,
    String title,
    String content,
    Long userId,
    int viewCount
){

    public PostRes fromEntity(Post post) {
        return new PostRes(post.getId(), post.getTitle(), post.getContent(), userId, post.getViewCount());
    }
}
