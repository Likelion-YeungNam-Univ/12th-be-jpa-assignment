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
    private String title;
    private String content;
    private String created_date;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now(); // 현재 시간으로 작성 시간 초기화
    private int read; // 조회수
    private int category_id;
    private int like_id;

    // 조회수 증가
    public void incrementReadCount() {
        this.read++;
    }
}
