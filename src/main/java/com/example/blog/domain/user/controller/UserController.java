package com.example.blog.domain.user.controller;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserCreateRequestDto;
import com.example.blog.domain.user.dto.UserPasswordRequestDto;
import com.example.blog.domain.user.dto.UserResponseDto;
import com.example.blog.domain.user.dto.UserUpdateRequestDto;
import com.example.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody UserCreateRequestDto request){
        User response = userService.create(request);
        return ResponseEntity.ok().body(response.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> read(@PathVariable Long id){
        UserResponseDto response = userService.read(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                       @RequestBody UserUpdateRequestDto request){
        userService.update(id, request);
        return ResponseEntity.ok().body("수정이 완료되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,
                                       @RequestBody UserUpdateRequestDto request){
        userService.delete(id, request);
        return ResponseEntity.ok().body("삭제가 완료되었습니다.");
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<String> resetPassword(@PathVariable Long id,
                                              @RequestBody UserPasswordRequestDto request){
        userService.resetPassword(id, request);
        return ResponseEntity.ok().body("비밀번호 변경이 완료되었습니다. 다시 로그인해주세요");
    }
}
