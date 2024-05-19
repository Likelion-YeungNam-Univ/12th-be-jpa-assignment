package com.example.blog.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Default constructor access restriction
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, length = 255)
    private String categoryContent;

    // Builder pattern
    @Builder
    public Category(String categoryContent) {
        this.categoryContent = categoryContent;
    }

    // Convenience method
    public void updateCategoryContent(String categoryContent) {
        if (categoryContent != null) {
            this.categoryContent = categoryContent;
        }
    }
}
