package com.example.blog.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자에 대한 접근 제한
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 255)
    private String loginId;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 255)
    private String nickname;

    @Column(length = 255)
    private String userEmail;

    @Column(nullable = false)
    private String userGender; // enum 타입이지만, 여기서는 단순화를 위해 String 사용

    @Column(nullable = false, length = 255)
    private String userIntroduce;

    @Column(nullable = false, length = 255)
    private String userImage;

    @Column(nullable = false)
    private Timestamp userCreatedDate;

    // 빌더 패턴
    @Builder
    public User(String loginId, String password, String nickname, String userEmail, String userGender, String userIntroduce, String userImage, Timestamp userCreatedDate) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userIntroduce = userIntroduce;
        this.userImage = userImage;
        this.userCreatedDate = userCreatedDate;
    }

    // 편의 메서드
    public void updateUserInfo(String password, String nickname, String userEmail, String userIntroduce, String userImage) {
        if (password != null) this.password = password;
        if (nickname != null) this.nickname = nickname;
        if (userEmail != null) this.userEmail = userEmail;
        if (userIntroduce != null) this.userIntroduce = userIntroduce;
        if (userImage != null) this.userImage = userImage;
    }
}
