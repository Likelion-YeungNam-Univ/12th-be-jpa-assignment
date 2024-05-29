package com.example.blog.domain.comment.domain;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String content;

    // 댓글 좋아요 기능 추가
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int likes;

    @ManyToMany
    @JoinTable(
            name = "comment_likes",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likedUsers = new HashSet<>();

    @Builder
    public Comment(Post post, User user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }


    // 댓글 좋아요
    public void like(User user) {
        if (likedUsers.add(user)) {
            this.likes++;
        }
    }

    // 댓글 좋아요 취소
    public void unlike(User user) {
        if (likedUsers.remove(user)) {
            this.likes--;
        }
    }



}
