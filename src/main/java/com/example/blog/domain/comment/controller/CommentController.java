package com.example.blog.domain.comment.controller;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentReq;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.comment.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentRes> createComment(@RequestBody CommentReq commentReq) {
        Comment comment = commentService.createComment(commentReq.userId(), commentReq.postId(), commentReq.toEntity());
        return ResponseEntity.ok(new CommentRes(comment.getId(), comment.getUser().getId(), comment.getPost().getId(), comment.getContent()));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentRes> getComment(@PathVariable Long commentId) {
        Comment comment = commentService.getComment(commentId);
        return ResponseEntity.ok(new CommentRes(comment.getId(), comment.getUser().getId(), comment.getPost().getId(), comment.getContent()));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentRes> updateComment(@PathVariable Long commentId, @RequestBody CommentReq commentReq) {
        Comment updatedComment = commentService.updateComment(commentId, commentReq.toEntity());
        return ResponseEntity.ok(new CommentRes(updatedComment.getId(), updatedComment.getUser().getId(), updatedComment.getPost().getId(), updatedComment.getContent()));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
