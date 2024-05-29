package com.example.blog.domain.comment.domain;

import com.example.blog.domain.like.domain.Good;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id")
    Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Good> likes = new ArrayList<>();

    private String content;




}
