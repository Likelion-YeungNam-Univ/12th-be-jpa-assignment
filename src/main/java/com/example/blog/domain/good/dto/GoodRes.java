package com.example.blog.domain.good.dto;

import com.example.blog.domain.good.domain.Good;

public record GoodRes (
        Long id,
        Long userId,
        Long commentId,
        boolean likeCheck

){
    public GoodRes fromEntity(Good good){
        return new GoodRes(good.getId(), userId, commentId, good.isLikeCheck());
        }
}
