package com.example.blog.domain.user.repository;

import com.example.blog.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserRepositoryTest {

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Repository에서 이메일로 유저 찾기")
    void 유저_이메일_찾기_테스트() {
        // given
        User user = User.builder()
                .email("test@test1.com")
                .password("test")
                .nickname("test1")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        // when
        userRepository.save(user);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // then
        assertThat(user).isEqualTo(userRepository.findByEmail(user.getEmail()).get());
    }

    @Test
    @DisplayName("Repository에서 닉네임으로 유저 찾기")
    void 유저_닉네임_찾기_테스트() {
        // given
        User user = User.builder()
                .email("test@test2.com")
                .password("test")
                .nickname("test2")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        // when
        userRepository.save(user);
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));

        // then
        assertThat(user).isEqualTo(userRepository.findByNickname(user.getNickname()).get());
    }
}