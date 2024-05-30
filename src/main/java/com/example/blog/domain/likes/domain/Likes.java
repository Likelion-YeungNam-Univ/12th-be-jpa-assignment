package com.example.blog.domain.likes.domain;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @Builder
    public Likes(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public void setUser(User user) {
        this.user = user;
        user.getLikes().add(this);
    }
    public void setComment(Comment comment) {
        this.comment = comment;
        comment.getLikes().add(this);
    }
}
