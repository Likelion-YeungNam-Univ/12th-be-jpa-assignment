package com.example.blog.domain.comment.controller;


import com.example.blog.domain.comment.dto.CommentRequest;
import com.example.blog.domain.comment.dto.CommentResponse;
import com.example.blog.domain.comment.service.CommentService;
import com.example.blog.domain.post.dto.PostRequest;
import com.example.blog.domain.post.dto.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<CommentResponse> comments = commentService.getAll();
        return ResponseEntity.ok(comments);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CommentRequest commentRequest){
        try {
            CommentResponse commentResponse= commentService.create(commentRequest);
            return ResponseEntity.ok(commentResponse);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
