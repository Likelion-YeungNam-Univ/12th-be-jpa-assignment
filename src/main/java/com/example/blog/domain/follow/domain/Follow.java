package com.example.blog.domain.follow.domain;

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
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long followId;

    // fk, 팔로워 userId, 임시로 설정
    @NotNull
    @Column(name = "follower_id")
    private Long followerId;

    // fk, 팔로잉 userId, 임시로 설정
    @NotNull
    @Column(name = "following_id")
    private Long followingId;

    @NotNull
    @Column(name = "follow_date")
    private LocalDateTime followDate;

    @Builder
    public Follow(Long followerId, Long followingId, LocalDateTime followDate) {
        this.followerId = followerId;
        this.followingId = followingId;
        this.followDate = LocalDateTime.now();
    }
}