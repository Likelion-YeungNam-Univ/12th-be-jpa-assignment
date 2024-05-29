package com.example.blog.domain.comment.domain;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.user.domain.User;
import com.example.blog.util.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @Column(name = "content", length = 300)
    @NotNull
    private String content;

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
