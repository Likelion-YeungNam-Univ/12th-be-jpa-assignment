package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;

public record PostRequestDto(
        Long userId,
        String title,
        String content
) {
    public Post toEntity(User user){
        return Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .build();
    }
}
