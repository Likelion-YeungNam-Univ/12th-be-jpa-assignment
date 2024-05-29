package com.example.blog.domain.comment.controller;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.comment.dto.CommentRequest;
import com.example.blog.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CommentRequest request){
        Comment response = commentService.create(request);
        return ResponseEntity.ok().body(response.getId());
    }
}
