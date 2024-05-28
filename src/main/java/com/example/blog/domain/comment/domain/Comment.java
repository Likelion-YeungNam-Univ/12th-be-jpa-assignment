package com.example.blog.domain.comment.domain;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "likes", nullable = false)
    private int likes;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(String content) {
        this.content = content;
        this.likes = 0;
    }

    public void addAssociate(User user, Post post){
        this.user = user;
        this.post = post;
    }

    public void update(String content) {
        this.content = content;
    }

    public void like(){
        this.likes++;
    }
    public void unlike(){
        this.likes--;
    }

}