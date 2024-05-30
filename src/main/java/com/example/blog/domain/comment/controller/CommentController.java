package com.example.blog.domain.comment.controller;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestParam Long postId, @RequestParam Long userId, @RequestBody String content) {
        Comment createdComment = commentService.createComment(postId, userId, content);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody String content) {
        Comment updatedComment = commentService.updateComment(commentId, content);
        return ResponseEntity.ok(updatedComment);
    }
}
