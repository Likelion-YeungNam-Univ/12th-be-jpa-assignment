package com.example.blog.domain.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long boardId;
    private LocalDateTime createdAt;

    @Builder
    public Comment(String content, Long boardId) {
        this.content = content;
        this.boardId = boardId;
    }

    public void update(String content){
        this.content = content;
    }
}
