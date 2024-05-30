package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;

public record PostResponse(
        String title,
        String content,
        Long postId
) {
    public static PostResponse fromEntity(Post post) {
        return new PostResponse(post.getTitle(), post.getContent(), post.getId());
    }
}
