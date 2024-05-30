package com.example.blog.domain.like.dto;

public record LikeReq(
        Long userId,
        Long commentId
) {}
