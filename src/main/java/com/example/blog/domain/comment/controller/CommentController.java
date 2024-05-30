package com.example.blog.domain.comment.controller;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.comment.dto.CommentRequest;
import com.example.blog.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comment")
    public ResponseEntity<?> create(@PathVariable Long postId,
                                    @RequestBody CommentRequest request){
        Comment response = commentService.create(postId, request);
        return ResponseEntity.ok().body(response.getId());
    }

    @PutMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<String> update(@PathVariable Long postId,
                                         @PathVariable Long commentId,
                                         @RequestBody CommentRequest request){
        commentService.update(commentId, request);
        return ResponseEntity.ok().body("수정이 완료되었습니다.");
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<String> delete(@PathVariable Long postId,
                                         @PathVariable Long commentId,
                                         @RequestBody CommentRequest request){
        commentService.delete(commentId, request);
        return ResponseEntity.ok().body("삭제가 완료되었습니다.");
    }
}
