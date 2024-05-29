package com.example.blog.domain.good.dto;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.good.domain.Good;

public record GoodReq (

        Long userId,
        Long commentId,
        boolean likeCheck

){
    public Good toEntity(){

        return Good.builder()

                .likeCheck(likeCheck)


                .build();
    }
}
