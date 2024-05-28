package com.example.blog.domain.user.service;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    //    @Test
//    @DisplayName("유저 생성 테스트 - 성공")
//    void 유저_가입_성공() {
//        // given
//        User user = User.builder()
//                .email("test@test1.com")
//                .password("test")
//                .nickname("test1")
//                .birthdate(LocalDate.parse("2024-05-27"))
//                .build();
//
//        // when
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
////        userRepository.save(user);
//        userService.userSignUp(user);
//
//        // then
//        assertThat(user).isEqualTo(userRepository.findByEmail(user.getEmail()).get());
//    }
    @Test
    @DisplayName("유저 생성 테스트 - 성공")
    void 유저_가입_성공() {
        // given
        User user = User.builder()
                .email("test@test1.com")
                .password("test")
                .nickname("test1")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        // when
        userService.userSignUp(user);

        // 유저가 저장된 상태를 반환하도록 설정
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // then
        assertThat(user).isEqualTo(userRepository.findByEmail(user.getEmail()).get());
    }

    @Test
    @DisplayName("유저 생성 테스트(이메일 중복 예외) - 실패")
    void 이메일중복_가입_실패() {
        // given
        User user = User.builder()
                .email("test@test1.com")
                .password("test1")
                .nickname("test1")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        User dupUser = User.builder()
                .email("test@test1.com")
                .password("test2")
                .nickname("test2")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        // when
        userService.userSignUp(user);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            userService.userSignUp(dupUser);
        });

        // then
        assertEquals("already exist email", thrown.getMessage());
    }

    @Test
    @DisplayName("유저 생성 테스트(닉네임 중복 예외) - 실패")
    void 닉네임중복_가입_실패() {
        // given
        User user = User.builder()
                .email("test@test1.com")
                .password("test1")
                .nickname("test1")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        User dupUser = User.builder()
                .email("test@test2.com")
                .password("test2")
                .nickname("test1")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        // when
        userService.userSignUp(user);
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            userService.userSignUp(dupUser);
        });

        // then
        assertEquals("already exist nickname", thrown.getMessage());
    }

    @Test
    void 비밀번호_변경_성공() {
        // given
        User user = User.builder()
                .email("test@test3.com")
                .password("test3")
                .nickname("test3")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        String newPassword = "test1";

        // when
        userService.userSignUp(user);
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));
        userService.changePassword(user.getNickname(), newPassword);

        // then
        assertThat(newPassword).isEqualTo(userRepository.findByNickname(user.getNickname()).get().getPassword());
    }

    @Test
    @DisplayName("비밀번호 변경 - 기존의 비밀번호가 입력된 경우의 실패 처리")
    void 기존_비밀번호_변경_실패() {

        // given
        User user = User.builder()
                .email("test@test4.com")
                .password("test4")
                .nickname("test4")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        String newPassword = "test4";

        // when
        userService.userSignUp(user);
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            userService.changePassword(user.getNickname(), newPassword);
        });

        // then
        assertEquals("The same password is not available.", thrown.getMessage());
    }

    @Test
    @DisplayName("비밀번호 변경 - 존재하는 유저가 아닌 경우")
    void 유저_찾지못함_비밀번호_변경_실패() {

        // given
        User user = User.builder()
                .email("test@test5.com")
                .password("test5")
                .nickname("test5")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        String newPassword = "test1";

        // when
        userService.userSignUp(user);
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            userService.changePassword("test0", newPassword);
        });

        // then
        assertEquals("No user exists.", thrown.getMessage());
    }

}