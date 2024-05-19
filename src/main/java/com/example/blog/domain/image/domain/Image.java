package com.example.blog.domain.image.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String path;

    // 빌더 패턴
    @Builder
    public Image(String path) {
        this.path = path;
    }

    // 편의 메서드
    public void updatePath(String path) {
        this.path = path;
    }

}
