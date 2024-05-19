package com.example.blog.domain.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String created_date; // 게시일

    @Column(nullable = false)
    private int read; // 조회수

    @Column(nullable = false)
    private int category_id;

    @Column(nullable = false)
    private int like_id;

    // 조회수 증가
    public void incrementReadCount() {
        this.read++;
    }
}
