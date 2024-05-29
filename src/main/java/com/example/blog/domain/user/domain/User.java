package com.example.blog.domain.user.domain;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.like.domain.Like;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 50, unique = true)
    @NotNull
    private String email;

    @Column(name = "password", length = 25)
    @NotNull
    private String password;

    @Column(name = "nickname", length = 25, unique = true)
    @NotNull
    private String nickname;

    @Column(name = "birthdate")
    @NotNull
    private LocalDate birthdate;

    @OneToOne(mappedBy = "user")
    private Blog blog;

    @OneToMany(mappedBy = "user")
    private List<Comment> userComments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();


    // 빌더 패턴
    @Builder
    public User(String email, String password, String nickname, LocalDate birthdate) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.birthdate = birthdate;
    }

    //편의 메서드
    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public void addLike(Like like) {
        likes.add(like);
        like.setUser(this);
    }

    public void removeLike(Like like) {
        likes.remove(like);
        like.setUser(null);
    }
}