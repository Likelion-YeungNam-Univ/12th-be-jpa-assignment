package com.example.blog.domain.user.dto;

import com.example.blog.domain.user.domain.User;

public record UserRequest(
        String username,
        String password,
        String email
) {
    public User toEntity(){
        return User.builder()
                .password(password)
                .username(username)
                .email(email)
                .build();
    }
}
