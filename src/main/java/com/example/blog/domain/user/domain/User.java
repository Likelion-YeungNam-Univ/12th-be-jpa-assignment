package com.example.blog.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String password;

    // 빌더 패턴
    @Builder
    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    //편의 메서드
    public void update(String nickname, String password) {
        this.nickname=nickname;
        this.password = password;
    }
}