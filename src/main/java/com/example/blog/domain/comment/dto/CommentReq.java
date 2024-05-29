package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.post.domain.Post;

public record CommentReq (

        Long userId,
        Long postId,
        String content

){
    public Comment toEntity(){

        return Comment.builder()

                .content(content)


                .build();
    }
}
