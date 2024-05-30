package com.example.blog.domain.comment.domain;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;

    private String content;

    private int likeCnt=0;

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt=likeCnt;
    }
}
