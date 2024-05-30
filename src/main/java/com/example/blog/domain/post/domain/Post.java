package com.example.blog.domain.post.domain;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "views", nullable = false)
    @ColumnDefault("0")
    private int views;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.views = 0;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void views(){
        this.views++;
    }

    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }
}