package com.example.blog.domain.user.dto;

import com.example.blog.domain.user.domain.User;

public record UserResponse(
        Long id,
        String username,
        String email
) {
    // 간단하게 파라미터가 여러개면? -> of, 단일 파라미터(주로 인스턴스)가 들어온다? -> from
    public static UserResponse of(Long id, String username, String email) {
        return new UserResponse(id, username, email);
    }

    public static UserResponse fromEntity(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}