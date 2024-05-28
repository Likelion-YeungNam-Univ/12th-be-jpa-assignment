package com.example.blog.user;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserReq;
import com.example.blog.domain.user.dto.UserRes;
import com.example.blog.domain.user.repository.UserRepository;
import com.example.blog.domain.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("사용자 생성 테스트")
    public void createUserTest() {
        // given
        UserReq userReq = UserReq.builder()
                .username("test")
                .email("test@gmail.com")
                .password("1234")
                .build();

        // when
        User createdUser = userService.createUser(userReq);

        // then
        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals("test", createdUser.getUsername());
        Assertions.assertEquals("test@gmail.com", createdUser.getEmail());
    }

    @Test
    @DisplayName("모든 사용자 조회 테스트")
    public void getAllUsersTest() {
        // given
        User user1 = User.builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("password1")
                .build();

        User user2 = User.builder()
                .username("user2")
                .email("user2@gmail.com")
                .password("password2")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        // when
        List<UserRes> users = userService.getAllUsers();

        // then
        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals("user1", users.get(0).getUsername());
        Assertions.assertEquals("user2", users.get(1).getUsername());
    }

    @Test
    @DisplayName("단일 사용자 조회 테스트")
    public void getUserTest() {
        // given
        User user = User.builder()
                .username("user1")
                .email("user1@gmail.com")
                .password("password1")
                .build();

        userRepository.save(user);

        // when
        UserRes foundUser = userService.getUser(user.getId());

        // then
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals("user1", foundUser.getUsername());
        Assertions.assertEquals("user1@gmail.com", foundUser.getEmail());
    }

    @Test
    @DisplayName("사용자 업데이트 테스트")
    public void updateUserTest() {
        // given
        User user = User.builder()
                .username("user")
                .email("user@gmail.com")
                .password("password")
                .build();

        User createUser = userRepository.save(user);

        UserReq userReq = UserReq.builder()
                .username("updateUser")
                .password("newPassword").build();

        // when
        UserRes updatedUser = userService.updateUser(createUser.getId(), userReq);

        // then
        Assertions.assertEquals("updateUser", updatedUser.getUsername());
    }

    @Test
    @DisplayName("사용자 삭제 테스트")
    public void deleteUserTest() {
        // given
        User user = User.builder()
                .username("user")
                .email("user@gmail.com")
                .password("password")
                .build();

        User createUser = userRepository.save(user);

        // when
        userService.deleteUser(createUser.getId());

        // then
        User deletedUser = userRepository.findById(user.getId()).orElse(null);
        Assertions.assertNull(deletedUser);
    }
}
