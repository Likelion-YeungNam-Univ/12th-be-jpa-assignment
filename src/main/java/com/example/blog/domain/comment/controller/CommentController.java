package com.example.blog.domain.comment.controller;


import com.example.blog.domain.comment.dto.CommentRequest;
import com.example.blog.domain.comment.dto.CommentResponse;
import com.example.blog.domain.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<?> getAll(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getAll(postId));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<?> create(@PathVariable Long postId, @RequestBody CommentRequest commentRequest){
        try {
            return ResponseEntity.ok(commentService.create(postId, commentRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{postId}/{commentId}")
    public ResponseEntity<?> get(@PathVariable Long postId, @PathVariable Long commentId){
        try {
            return ResponseEntity.ok(commentService.get(postId, commentId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{postId}/{commentId}")
    public ResponseEntity<?> update(@PathVariable Long postId, @PathVariable Long commentId,@RequestBody CommentRequest commentRequest){
        try{
            return ResponseEntity.ok(commentService.update(postId, commentId, commentRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<?> delete(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.delete(postId, commentId);
        return ResponseEntity.ok("댓글 삭제 완료");
    }
}
