package com.example.blog.domain.like.controller;

import com.example.blog.domain.like.dto.LikeReq;
import com.example.blog.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/commentlike")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    @PostMapping("")
    public ResponseEntity<Void> toggleLike(@RequestBody LikeReq likeReq) {
        likeService.toggleLike(likeReq);
        return ResponseEntity.ok().build();
    }

}
