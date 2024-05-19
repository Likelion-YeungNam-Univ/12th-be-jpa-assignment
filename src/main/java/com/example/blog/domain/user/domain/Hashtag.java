package com.example.blog.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자의 접근 제한
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtagId;

    @Column(nullable = false, length = 255)
    private String tagContent;

    // 빌더 패턴
    @Builder
    public Hashtag(String tagContent) {
        this.tagContent = tagContent;
    }

    // 편의 메소드
    public void updateTagContent(String tagContent) {
        if (tagContent != null && !tagContent.isEmpty()) {
            this.tagContent = tagContent;
        }
    }
}
