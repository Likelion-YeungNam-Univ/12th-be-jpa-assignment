package com.example.blog.domain.post.dto;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import lombok.Getter;

@Getter
public class PostRes {

    private final Long id;
    private final String title;
    private final String content;
    private final String username;
    private final Integer view;

    public PostRes(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUser().getUsername();
        this.view = post.getView();
    }
}
