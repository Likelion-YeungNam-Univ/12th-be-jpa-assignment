package com.example.blog.domain.comment.domain;

import com.example.blog.domain.like.domain.Like;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne     // N:1 관계
    @JoinColumn(name = "post_id")    // 외래 키 지정
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    /* 작성자 설정 */
    public void setUser(User user) {
        this.user = user;
    }

    /* 게시글 설정 */
    public void setPost(Post post) {
        this.post = post;
    }
    public void updateContent(String content) {
        this.content = content;
    }
}