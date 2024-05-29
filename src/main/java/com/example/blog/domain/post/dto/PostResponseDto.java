package com.example.blog.domain.post.dto;

import com.example.blog.domain.comment.dto.CommentListResponse;
import com.example.blog.domain.post.domain.Post;

import java.util.List;

public record PostReadResponseDto(
        String username,
        String title,
        String content,
        int viewCount,
        List<CommentListResponse> comments
) {
    public static PostReadResponseDto fromEntity(Post post){
        return new PostReadResponseDto(
                post.getUser().getUsername(),
                post.getTitle(),
                post.getContent(),
                post.getViewCount(),
                post.getComments().stream().map(CommentListResponse::fromEntity).toList()
        );
    }
}
