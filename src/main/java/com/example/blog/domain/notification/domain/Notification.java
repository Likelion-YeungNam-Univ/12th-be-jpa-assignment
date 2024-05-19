package com.example.blog.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

import java.time.LocalDateTime;

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long boardId;
    private NotificationType notificationType;
    private LocalDateTime createdAt;

    @Builder
    public Notification(Long userId, Long boardId, NotificationType notificationType) {
        this.userId = userId;
        this.boardId = boardId;
        this.notificationType = notificationType;
    }
}
