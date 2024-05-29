package com.example.blog.domain.like.controller;

import com.example.blog.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LikeController {

    private final LikeService likeService;

    /* 가게 좋아요 증가 */
    @PostMapping("/{userId}/like/{commentId}")
    public ResponseEntity<String> likeRestaurant(@PathVariable Long userId, @PathVariable Long commentId) {

        likeService.likeComment(userId, commentId);
        return ResponseEntity.ok("댓글 좋아요 증가.");
    }

    /* 가게 좋아요 감소 */
    @DeleteMapping("/{userId}/like/{commentId}")
    public ResponseEntity<String> unLikeRestaurant(@PathVariable Long userId, @PathVariable Long commentId) {

        likeService.unLikeComment(userId, commentId);
        return ResponseEntity.ok("댓글 좋아요 감소.");
    }
}
