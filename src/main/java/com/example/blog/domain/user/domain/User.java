package com.example.blog.domain.user.domain;

import com.example.blog.global.enums.LoginType;
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
    private Long userId;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    // 빌더 패턴
    @Builder
    public User(String email, String nickname, String password, String phoneNumber) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    //편의 메서드
    public void modifyProfile(String email, String nickname,String phoneNumber) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}