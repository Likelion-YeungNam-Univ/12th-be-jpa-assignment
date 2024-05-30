package com.example.blog.domain.user.dto;

import com.example.blog.domain.user.domain.User;

public record UserRes(
        Long id,
        String username,
        String email
) {
    // 간단하게 파라미터가 여러개면? -> of, 단일 파라미터(주로 인스턴스)가 들어온다? -> from
    public static UserRes of(Long id, String username, String email) {
        return new UserRes(id, username, email);
    }

    public static UserRes fromEntity(User user) {
        return new UserRes(user.getId(), user.getUsername(), user.getEmail());
    }
}
