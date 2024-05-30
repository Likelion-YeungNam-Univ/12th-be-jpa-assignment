package com.example.blog.domain.user.dto;

import com.example.blog.domain.user.domain.User;

public record UserUpdateRequest(
        Long userId,
        String email
) {
    public User toEntity(){
        return User.builder()
                .email(email)
                .build();
    }
}
