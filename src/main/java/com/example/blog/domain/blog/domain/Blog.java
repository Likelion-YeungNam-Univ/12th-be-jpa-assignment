package com.example.blog.domain.blog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 60, nullable = false)
    private String blogUrl;

    @Builder
    public Blog(String name, String blogUrl) {
        this.name = name;
        this.blogUrl = blogUrl;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
