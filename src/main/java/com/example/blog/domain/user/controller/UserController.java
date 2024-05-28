package com.example.blog.domain.user.controller;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserRequest;
import com.example.blog.domain.user.dto.UserResponse;
import com.example.blog.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        try {
            UserResponse userResponse = userService.getUser(userId);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userReq){
        User user = userService.createUser(userReq);
        UserResponse userRes = UserResponse.fromEntity(user);

        return ResponseEntity.ok(userRes);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserRequest userReq){
        try{
            return ResponseEntity.ok(userService.updateUser(userId, userReq));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("삭제 완료");
    }

}
