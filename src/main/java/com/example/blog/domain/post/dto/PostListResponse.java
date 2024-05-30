package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;

public record PostListResponse(
        Long postId,
        String title,
        int viewCount
) {
    public static PostListResponse fromEntity(Post post){
        return new PostListResponse(
                post.getId(),
                post.getTitle(),
                post.getViewCount()
        );
    }
}
