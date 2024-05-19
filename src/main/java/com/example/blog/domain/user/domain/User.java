package com.example.blog.domain.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 50)
    @NotNull
    private String email;

    @Column(name = "password", length = 25)
    @NotNull
    private String password;

    @Column(name = "nickname", length = 25)
    @NotNull
    private String nickname;

    @Column(name = "birthdate")
    @NotNull
    private LocalDate birthdate;

    // 빌더 패턴
    @Builder
    public User(String email, String password, String nickname, LocalDate birthdate) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.birthdate = birthdate;
    }

    //편의 메서드
    public void updatePassword(String password) {
        this.password = password;
    }
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}