package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
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
