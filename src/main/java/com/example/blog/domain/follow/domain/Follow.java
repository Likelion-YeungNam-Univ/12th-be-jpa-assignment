package com.example.blog.domain.follow.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followid")
    private Long followId;

    //fk, 팔로워 userId
    @Column(name = "followerid", nullable = false)
    private Long followerId;

    //fk, 팔로잉 userId
    @Column(name = "followingid", nullable = false)
    private Long followingId;

    @Column(name = "follow_date", nullable = false)
    private LocalDateTime followDate;

    @Builder
    public Follow(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
        this.followDate = LocalDateTime.now();
    }
}
