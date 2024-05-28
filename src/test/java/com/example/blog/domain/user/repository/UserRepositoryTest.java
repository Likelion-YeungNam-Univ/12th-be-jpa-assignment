package com.example.blog.domain.user.repository;

import com.example.blog.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저 생성")
    void createUser() {
        User fisrtUser = User.builder()
                .email("test1111@test.com")
                .password("testPassword1111")
                .nickname("testNickname1111")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        User secondUser = User.builder()
                .email("test2222@test.com")
                .password("testPassword2222")
                .nickname("testNickname2222")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();


    }

}