package com.example.blog.domain.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userid;

    @Column(name = "user_name")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "profile_image")
    @NotNull
    private String profileImage;

    @Column(name = "register_date")
    @NotNull
    private LocalDateTime registerDate;

    @Column(name = "active")
    @NotNull
    @ColumnDefault("true")
    private boolean active;

    @Builder
    public User(String username, String password, String email, String profileImage, LocalDateTime registerDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImage = profileImage;
        this.registerDate = registerDate;
    }

    // 편의 메서드
    //수정
    public void updateUsername(String username) {
        this.username = username;
    }
    public void updatePassword(String password) {
        this.password=password;
    }
    public void updateEmail(String email) {
        this.email = email;
    }
    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    public void updateUserInfo(String username, String email, String profileImage) {
        this.username = username;
        this.email = email;
        this.profileImage = profileImage;
    }

    //비밀번호 검증
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    //탈퇴
    public void deactivateUser() {
        this.active = false;
    }
    public void activateUser() {
        this.active = true;
    }
}
