package com.example.blog.domain.user.controller;

import com.example.blog.domain.user.dto.PasswordDto;
import com.example.blog.domain.user.dto.UserReq;
import com.example.blog.domain.user.dto.UserRes;
import com.example.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /* 사용자 생성 */
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserReq userReq) {

        return ResponseEntity.ok(userService.createUser(userReq));
    }

    /* 전체 사용자 불러오기 */
    @GetMapping
    public List<UserRes> readAllUser() {
        return userService.getAllUsers();
    }

    /* 특정 사용자 불러오기 */
    @GetMapping("/{userId}")
    public UserRes readUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    /* 회원 업데이트 */
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserReq userReq) {
        return ResponseEntity.ok(userService.updateUser(userId, userReq));
    }

    /* 회원 삭제 */
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("사용자 삭제 성공");
    }

    /* 사용자 비밀번호 재설정 */
    @PatchMapping("/{userId}")
    public ResponseEntity<?> resetUserPass(@PathVariable Long userId,
                                           @RequestBody PasswordDto passwordDto) {

        return ResponseEntity.ok(userService.resetUserPassword(userId, passwordDto));
    }

}
