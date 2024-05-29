package com.example.blog.domain.like.dto;

import com.example.blog.domain.like.domain.CommentLike;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostRes;

public record LikeRes(
        String username,
        String content

) {
    public static LikeRes fromEntity(CommentLike like) {
        return new LikeRes(
                like.getUser().getUsername(), like.getComment().getContent()
        );
    }
}
