package com.example.blog.domain.user.domain;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.like.domain.Like;
import com.example.blog.domain.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user",  fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",  fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",  fetch = FetchType.EAGER)
    private List<Like> likes = new ArrayList<>();

    // 빌더 패턴
    @Builder
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     비밀번호를 변경한다.
     @param password 비밀번호
     */
    public void updatePwd(String password) {
        this.password = password;
    }
}