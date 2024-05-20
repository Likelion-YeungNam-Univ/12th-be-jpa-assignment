package com.example.blog.domain.category.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotNull
    @Column(name = "category_name")
    private String categoryName;

    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    //수정
    public void updateCateoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}