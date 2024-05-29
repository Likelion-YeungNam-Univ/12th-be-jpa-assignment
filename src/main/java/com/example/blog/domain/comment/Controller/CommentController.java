package com.example.blog.domain.comment.Controller;


import com.example.blog.domain.comment.dto.CommentReq;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentRes> createComment(@RequestBody CommentReq commentReq) {
        CommentRes comment = commentService.createComment(commentReq);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentRes>> getCommentsByPostId(@PathVariable Long postId) {
        List<CommentRes> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentRes>> getCommentsByUserId(@PathVariable Long userId) {
        List<CommentRes> comments = commentService.getCommentsByUserId(userId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentRes> updateComment(@PathVariable Long commentId, @RequestBody CommentReq commentReq) {
        CommentRes updatedComment = commentService.updateComment(commentId, commentReq.getContent());
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
