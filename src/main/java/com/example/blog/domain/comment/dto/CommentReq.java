package com.example.blog.domain.comment.dto;

import com.example.blog.domain.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class CommentReq {

    private String content;

    public Comment toCommentEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
