package com.example.blog.domain.like.domain;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@RequiredArgsConstructor
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "count")
    private Long count;

    @Builder
    public Like(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
        this.count = 0L;
    }

    // 좋아요 클릭
    public void clickLike() {
        this.count++;
    }

    // 좋아요 취소
    public void cancelLike() {
        this.count--;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
