package com.example.blog.domain.like.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count")
    private Long count;

    // 좋아요 클릭
    public void clickLike() {
        this.count++;
    }
    
    // 좋아요 취소
    public void cancelLike() {
        this.count--;
    }
}
