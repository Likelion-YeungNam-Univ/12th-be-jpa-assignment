package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;

public record PostRequest(
        String title,
        String content,
        Long userId
) {

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
