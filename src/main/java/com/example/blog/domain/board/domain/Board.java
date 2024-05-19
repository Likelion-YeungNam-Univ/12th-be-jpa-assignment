package com.example.blog.domain.board.domain;

import com.example.blog.global.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private int viewCount;

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
        this.viewCount = 0;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void view(){
        viewCount++;
    }
}
