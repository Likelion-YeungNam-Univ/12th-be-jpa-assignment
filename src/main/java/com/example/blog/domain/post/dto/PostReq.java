package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostReq {

    private String title;
    private String content;

    public Post toPostEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
