package com.example.blog.domain.reply.domain;

import jakarta.persistence.*;
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


    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "reply_date", nullable = false)
    private LocalDateTime replyDate;

    @Builder
    public Reply(String content) {
        this.content = content;
        this.replyDate = LocalDateTime.now();
    }

    //수정
    public void updateContent(String content) {
        this.content = content;
    }
}