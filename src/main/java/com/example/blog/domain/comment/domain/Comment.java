package com.example.blog.domain.comment.domain;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @Setter
    private int likes;

    @Builder
    public Comment(Post post, User user, String content, int likes) {
        this.post = post;
        this.user = user;
        this.content = content;
        this.likes = likes;
    }

    public void update(String content) {
        this.content = content;
    }
    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }
    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }
}

