package com.example.blog.domain.user.dto;

import com.example.blog.domain.user.domain.User;

public record UserCreateRequestDto(
        String username,
        String password,
        String email
) {
    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
