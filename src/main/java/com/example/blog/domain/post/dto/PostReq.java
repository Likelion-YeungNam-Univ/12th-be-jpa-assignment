package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;

public record PostReq(
        Long userId,
        String title,
        String content
) {
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .view(0) // 조회수 초기화
                .build();
    }


}