package com.example.blog.domain.comment.domain;

import com.example.blog.domain.good.domain.Good;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Good> likes = new ArrayList<>();

    private String content;

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

}
