package com.example.blog.domain.user.domain;

import jakarta.persistence.*;

@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followId;

    @Column(name = "following_id", nullable = false)
    private int followingId;

    @Column(name = "follower_id", nullable = false)
    private int followerId;

    public Follow() {
    }

    public Follow(int followingId, int followerId) {
        this.followingId = followingId;
        this.followerId = followerId;
    }

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }
}
