package com.example.blog.domain.user.dto;

public record UserPasswordRequestDto (
        Long userId,
        String password
){
}
