package com.example.blog.domain.like.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @NotNull
    @Column(name = "postid")
    private Long postId;

    @NotNull
    @Column(name = "like_date")
    private LocalDateTime likeDate;

    @Builder

    public Like(Long postId) {
        this.postId = postId;
        this.likeDate = LocalDateTime.now();
    }
}