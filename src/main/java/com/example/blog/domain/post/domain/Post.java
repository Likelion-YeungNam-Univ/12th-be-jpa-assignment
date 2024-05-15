package com.example.blog.domain.post.domain;

import com.example.blog.domain.BaseTimeEntity;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private int view;

    /* 연관관계를 위한 임시 필드
    private User user;

    private Category category;
    */

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 게시글 업데이트
    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 조회수 증가.(임시. 나중에는 JPA 레포지토리 네이밍 함수를 사용)
    public void increaseView() {
        this.view++;
    }
}
