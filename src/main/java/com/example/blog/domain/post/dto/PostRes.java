package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;

public record PostRes(
        String writer,
        Long id,
        String title,
        String content,
        int view
) {
    public static PostRes fromEntity(Post post) {
        return new PostRes(
                post.getUser().getUsername(), post.getId(), post.getTitle(), post.getContent(), post.getView()
        );
    }
}
