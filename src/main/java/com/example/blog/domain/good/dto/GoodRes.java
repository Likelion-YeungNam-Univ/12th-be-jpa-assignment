package com.example.blog.domain.good.dto;

import com.example.blog.domain.good.domain.Good;

public record GoodRes (
        Long userId,
        Long commentId,
        boolean likeCheck

){
    public GoodRes fromEntity(Good good){
        return new GoodRes(userId, commentId, good.isLikeCheck());
        }
}
