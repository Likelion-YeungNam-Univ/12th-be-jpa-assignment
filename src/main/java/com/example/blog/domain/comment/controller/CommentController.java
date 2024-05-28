package com.example.blog.domain.comment.controller;

import com.example.blog.domain.comment.dto.CommentReq;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    /* 댓글 작성하기 */
    @PostMapping("/{postId}/comments/{userId}")
    public ResponseEntity<?> saveComment(@PathVariable Long postId,
                                         @PathVariable Long userId,
                                         @RequestBody CommentReq commentReq) {

        return ResponseEntity.ok(commentService.save(userId, postId, commentReq));
    }

    /* 게시글 댓글 불러오기 */
    @GetMapping("/{postId}/comments")
    public List<CommentRes> readAllComment(@PathVariable Long postId) {

        return commentService.findAll(postId);
    }

    /* 댓글 업데이트 */
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId,
                                           @RequestBody CommentReq commentReq) {

        return ResponseEntity.ok(commentService.update(commentId, commentReq));
    }

    /* 댓글 삭제 */
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {

        commentService.delete(commentId);
        return ResponseEntity.ok().body("댓글 삭제 성공.");
    }
}
