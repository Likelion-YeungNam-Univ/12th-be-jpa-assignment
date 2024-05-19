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
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    // 추가
    private String nickname;
    private String gender;
    private String phone;
    private String profileImg;
    private String introduce;


    // 빌더 패턴
    @Builder
    public User(String username, String password, String nickname, String gender, String phone, String profileImg, String introduce) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
        this.profileImg = profileImg;
        this.introduce =introduce;
    }

    //편의 메서드
    public void update(String username, String password, String nickname, String gender, String phone, String profileImg, String introduce) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
        this.profileImg = profileImg;
        this.introduce =introduce;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}