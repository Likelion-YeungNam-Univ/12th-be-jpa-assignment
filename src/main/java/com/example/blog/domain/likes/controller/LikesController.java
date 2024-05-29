package com.example.blog.domain.likes.controller;

import com.example.blog.domain.likes.domain.Likes;
import com.example.blog.domain.likes.dto.LikeRequest;
import com.example.blog.domain.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody LikeRequest request){
        Likes likes = likesService.create(request);
        return ResponseEntity.ok().body(likes.getId());
    }

    @DeleteMapping("")
    public ResponseEntity<String> delete(@RequestBody LikeRequest request){
        likesService.delete(request);
        return ResponseEntity.ok().body("좋아요가 취소되었습니다.");
    }
}
