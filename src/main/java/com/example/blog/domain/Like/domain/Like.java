package com.example.blog.domain.Like.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeid")
    private Long likeId;

    //fk
    @Column(name = "userid", nullable = false)
    private Long userId;

    @Column(name = "postid", nullable = false)
    private Long postId;

    @Column(name = "like_date", nullable = false)
    private LocalDateTime likeDate;

    @Builder

    public Like(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
        this.likeDate = LocalDateTime.now();
    }
}