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

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{commentId}/like")
    public ResponseEntity<Comment> likeComment(@PathVariable Long commentId) {
        Comment likedComment = commentService.likeComment(commentId);
        return ResponseEntity.ok(likedComment);
    }

    @PostMapping("/{commentId}/unlike")
    public ResponseEntity<Comment> unlikeComment(@PathVariable Long commentId) {
        Comment unlikedComment = commentService.unlikeComment(commentId);
        return ResponseEntity.ok(unlikedComment);
    }
}
