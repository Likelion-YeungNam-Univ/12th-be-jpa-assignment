package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;


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
