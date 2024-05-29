package com.example.blog.domain.like.domain;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Likes(User user, Post post, Comment comment) {
        this.user = user;
        this.post = post;
        this.comment = comment;
    }
    @Builder
    public static Likes of(User user, Post post, Comment comment) {
        return new Likes(user, post, comment);
    }
}