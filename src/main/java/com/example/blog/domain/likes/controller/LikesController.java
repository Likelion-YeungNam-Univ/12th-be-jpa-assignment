package com.example.blog.domain.likes.controller;

import com.example.blog.domain.likes.domain.Likes;
import com.example.blog.domain.likes.dto.LikeRequest;
import com.example.blog.domain.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/{postId}/comment/{commentId}/likes")
    public ResponseEntity<String> create(@PathVariable Long postId,
                                         @PathVariable Long commentId,
                                         @RequestBody LikeRequest request){
        Likes response = likesService.create(commentId, request);
        return ResponseEntity.ok().body("좋아요가 생성되었습니다.");
    }

    @DeleteMapping("/{postId}/comment/{commentId}/likes")
    public ResponseEntity<String> delete(@PathVariable Long postId,
                                         @PathVariable Long commentId,
                                         @RequestBody LikeRequest request){
        likesService.delete(commentId, request);
        return ResponseEntity.ok().body("좋아요가 취소되었습니다.");
    }
}
