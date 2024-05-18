package com.example.blog.domain.postcategory.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    //fk, 다대다 중간테이블이라 임시로 설정
    @NotNull
    @Column(name = "post_id")
    private Long postId;

    @NotNull
    @Column(name = "category_id")
    private Long categoryId;

    @Builder
    public PostCategory(Long postId, Long categoryId) {
        this.postId = postId;
        this.categoryId = categoryId;
    }

}
