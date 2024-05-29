package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;

public record PostReadResponseDto(
        String username,
        String title,
        String content,
        int viewCount
) {
    public static PostReadResponseDto fromEntity(Post post){
        return new PostReadResponseDto(
                post.getUser().getUsername(),
                post.getTitle(),
                post.getContent(),
                post.getViewCount()
        );
    }
}
