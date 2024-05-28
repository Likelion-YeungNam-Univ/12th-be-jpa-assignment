package com.example.blog.domain.user.dto;

import com.example.blog.domain.user.domain.User;

public record UserReq(
        String username,
        String email,
        String password
) {
    public User toEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}

