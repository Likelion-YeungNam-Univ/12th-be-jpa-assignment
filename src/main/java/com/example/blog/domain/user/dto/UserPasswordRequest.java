package com.example.blog.domain.user.dto;

public record UserPasswordRequest(
        Long userId,
        String password
){
}
