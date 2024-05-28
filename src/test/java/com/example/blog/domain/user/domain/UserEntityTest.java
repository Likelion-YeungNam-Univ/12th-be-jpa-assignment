package com.example.blog.domain.user.domain;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void 유저_등록_테스트() {
        User user = User.builder()
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

//        System.out.println(user.getId());
        // id값은 DB에서 처리되는 거라서 null로 되는건가?
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getNickname());
        System.out.println(user.getBirthdate());
    }

    @Test
    void 유저_비밀번호_변경() {
        User user = User.builder()
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        String newPassoword = "test1234";

        System.out.println("변경 전 : " + user.getPassword());
        user.updatePassword(newPassoword);
        System.out.println("변경 후 : " + user.getPassword());
    }

    @Test
    void 유저_별명_변경() {
        User user = User.builder()
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        String newNickname = "newTest";

        System.out.println("변경 전 : " + user.getNickname());
        user.updateNickname(newNickname);
        System.out.println("변경 후 : " + user.getNickname());

    }
}