package com.example.blog.domain.user.controller;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserCreateRequestDto;
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
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @RequestBody UserUpdateRequestDto request){
        User response = userService.update(id, request);
        return ResponseEntity.ok().body(response.getId());
    }
}
