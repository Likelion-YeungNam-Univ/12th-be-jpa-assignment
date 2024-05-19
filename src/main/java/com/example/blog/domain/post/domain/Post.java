package com.example.blog.domain.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "post_date")
    private LocalDateTime postDate;

    // 조회수
    @NotNull
    @Column(name = "views")
    @ColumnDefault("0")
    private int views;

    // 좋아요 수
    @NotNull
    @Column(name = "like_cnt")
    @ColumnDefault("0")
    private int likeCnt;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.postDate = LocalDateTime.now();
    }
    //수정 메소드
    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateContent(String content) {
        this.content = content;
    }

    //조회수 증가
    public void updateViews() {
        this.views++;
    }
    //좋아요 증가
    public void upLikeCnt() {
        this.likeCnt++;
    }
    //좋아요 취소
    public void downLikeCnt() {
        if(this.likeCnt>0) {
            this.likeCnt--;
        }
    }
}