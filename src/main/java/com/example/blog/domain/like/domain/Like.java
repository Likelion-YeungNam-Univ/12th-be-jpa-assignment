package com.example.blog.domain.like.domain;

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
    @Column(name = "like_id")
    private Long likeId;

    @Column(name = "postid", nullable = false)
    private Long postId;

    @Column(name = "like_date", nullable = false)
    private LocalDateTime likeDate;

    @Builder

    public Like(Long postId) {
        this.postId = postId;
        this.likeDate = LocalDateTime.now();
    }
}
