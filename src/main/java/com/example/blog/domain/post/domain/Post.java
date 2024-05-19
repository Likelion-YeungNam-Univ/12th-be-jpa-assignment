package com.example.blog.domain.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    private Long postId;

    //fk
    @Column(name = "userid", nullable = false)
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "post_date", nullable = false)
    private LocalDateTime postDate;

    //조회수
    @Column(name = "views", nullable = false)
    private int views;

    //좋아요수
    @Column(name = "like_cnt", nullable = false)
    private int likeCnt;

    @Builder
    public Post(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.postDate = LocalDateTime.now();
        this.views = 0;
        this.likeCnt = 0;
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
        if(this.likeCnt>0) {this.likeCnt--;}
    }
}