package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;

public record PostListResponseDto(
        Long postId,
        String title,
        int viewCount
) {
    public static PostListResponseDto fromEntity(Post post){
        return new PostListResponseDto(
                post.getId(),
                post.getTitle(),
                post.getViewCount()
        );
    }
}
