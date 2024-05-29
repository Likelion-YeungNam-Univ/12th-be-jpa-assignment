package com.example.blog.domain.post.domain;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private String title;
    private String content;
    @ColumnDefault("0")
    private int viewCount;

    @Builder
    public Post(User user, String title, String content, int viewCount) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }

    // 편의 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 연관관계 편의 메서드
    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    public void increaseViewCount(){
        this.viewCount++;
    }
}