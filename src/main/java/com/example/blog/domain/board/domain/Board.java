package com.example.blog.domain.board.domain;

import com.example.blog.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "content", length = 1024)
    @ColumnDefault("")
    private String content;

    @Column(name = "createdAt", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "view")
    @ColumnDefault("0")
    private int view;

    @Builder
    public Board (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void increaseView() {
        this.view++;
    }
}
