package com.example.blog.domain.like.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count")
    @ColumnDefault("0")
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
