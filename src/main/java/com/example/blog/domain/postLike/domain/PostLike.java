package com.example.blog.domain.likes.domain;

import com.example.blog.domain.BaseTimeEntity;
import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Likes extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;

    private Post post;

    private Comment comment;

    // 좋아요 한 사용자 등록
    public void setUser(User user) {
        this.user = user;
    }

    // 사용자가 좋아요한 글 등록
    public void setPost(Post post) {
        this.post = post;
    }

    // 사용자가 좋아요한 댓글 등록
    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
