package com.example.blog.domain.board.domain;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.user.domain.User;
import com.example.blog.util.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "blog_id")
    Blog blog;

    @Column(name = "title", length = 30)
    @NotNull
    private String title;

    @Column(name = "content", length = 1024)
    @ColumnDefault("''")
    private String content;

    @Column(name = "view_count")
    @ColumnDefault("0")
    private int viewCount;

    @Builder
    public Board (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void increaseView() {
        this.viewCount++;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
        this.user = blog.getUser();
        this.blog.getBoards().add(this);
    }
}
