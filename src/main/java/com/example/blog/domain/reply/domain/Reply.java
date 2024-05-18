package com.example.blog.domain.reply.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long replyId;

    @NotNull
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "reply_date")
    private LocalDateTime replyDate;

    @Builder
    public Reply(String content) {
        this.content = content;
        this.replyDate = LocalDateTime.now();
    }

    // 수정
    public void updateContent(String content) {
        this.content = content;
    }
}
