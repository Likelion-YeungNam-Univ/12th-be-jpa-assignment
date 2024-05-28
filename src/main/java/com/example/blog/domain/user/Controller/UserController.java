package com.example.blog.domain.user.Controller;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.PwdDto;
import com.example.blog.domain.user.dto.UserReq;
import com.example.blog.domain.user.dto.UserRes;
import com.example.blog.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<UserRes> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}") // id -> userId로 변경
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        User user = userService.getUser(userId);
        UserRes userRes = UserRes.fromEntity(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserReq userReq){
        User user = userService.createUser(userReq);
        UserRes userRes = UserRes.fromEntity(user);
        return ResponseEntity.ok(userRes);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserReq userReq){
        User user = userService.updateUser(userId, userReq);
        UserRes userRes = UserRes.fromEntity(user);
        return ResponseEntity.ok(userRes);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    // 비밀번호 재생성 API 생성
    @PutMapping("/{userId}/reset-password")
    public ResponseEntity<?> resetPassword(@PathVariable Long userId, @RequestBody PwdDto pwdDto) {
        User user = userService.resetPassword(userId, pwdDto);
        UserRes userRes = UserRes.fromEntity(user);
        return ResponseEntity.ok(userRes);
    }

}
