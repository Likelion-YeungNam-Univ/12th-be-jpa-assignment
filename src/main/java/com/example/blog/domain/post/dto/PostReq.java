package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;

public record PostReq (

        Long userId,
    String content,
    String title

){
    public Post toEntity(){

        return Post.builder()

                .content(content)
                .title(title)

                .build();
    }
}
