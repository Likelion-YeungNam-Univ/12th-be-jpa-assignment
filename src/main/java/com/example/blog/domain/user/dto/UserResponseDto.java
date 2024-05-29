package com.example.blog.domain.user.dto;

import com.example.blog.domain.post.dto.PostListResponseDto;
import com.example.blog.domain.user.domain.User;

import java.util.List;

public record UserResponseDto (
        String email,
        List<PostListResponseDto> postList
){
    public static UserResponseDto fromEntity(User user){
        return new UserResponseDto(
                user.getEmail(),
                user.getPosts().stream().map(PostListResponseDto::fromEntity).toList());
    }
}
