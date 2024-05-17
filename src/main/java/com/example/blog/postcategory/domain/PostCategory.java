package com.example.blog.postcategory.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //fk
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Builder
    public PostCategory(Long postId, Long categoryId) {
        this.postId = postId;
        this.categoryId = categoryId;
    }

}
