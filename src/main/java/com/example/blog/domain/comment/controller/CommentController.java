package com.example.blog.domain.comment.controller;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentReq;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.comment.service.CommentService;
import com.example.blog.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("")
    public ResponseEntity<?> getAllComments() {
        List<CommentRes> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
    @PostMapping("/post/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody CommentReq commentReq) {
        Comment comment = commentService.createComment(postId, commentReq);
        return ResponseEntity.ok(comment);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable Long id) {
        Comment comment = commentService.getComment(id);
        CommentRes commentRes= CommentRes.fromEntity(comment);
        return ResponseEntity.ok(commentRes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentReq commentReq) {
        Comment updateComment = commentService.updateComment(id, commentReq.toEntity());
        CommentRes commentRes = CommentRes.fromEntity(updateComment);
        return ResponseEntity.ok(commentRes);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long postId) {
        commentService.deleteComment(postId);
        return ResponseEntity.ok().build();
    }

    //게시물 아이디로 댓글 목록 가져오기
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPost(postId);
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

}
