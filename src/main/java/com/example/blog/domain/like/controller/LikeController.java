package com.example.blog.domain.like.controller;

import com.example.blog.domain.comment.dto.CommentResponse;
import com.example.blog.domain.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping("/{postId}/{commentId}")
    public ResponseEntity<?> Like(@PathVariable Long postId, @PathVariable Long commentId) {
        try {
            CommentResponse commentResponse = likeService.likeOrUnlike(postId, commentId);
            return ResponseEntity.ok(commentResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
