package com.example.blog.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReq {
    private Long postId;
    private Long userId;
    private String content;
}
