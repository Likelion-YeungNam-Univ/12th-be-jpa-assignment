package com.example.blog.domain.comment.domain;

import com.example.blog.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", length = 300,  nullable = false)
    private String content;

    @Column(name = "createdAt", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
