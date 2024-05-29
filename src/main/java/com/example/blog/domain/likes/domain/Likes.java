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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Comment comment;

    @Builder
    public Likes(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }
}
