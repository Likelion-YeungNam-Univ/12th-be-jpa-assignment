package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;

public record PostRes(
    Long id,
    String title,
    String content,
    Long userId,
    int view // 조회수 추가
) {

    public static PostRes fromEntity(Post post) {
        return new PostRes(post.getId(), post.getTitle(), post.getContent(), post.getUser().getId(), post.getView()); // 게시글 조회 빌더 추가
    }
}

