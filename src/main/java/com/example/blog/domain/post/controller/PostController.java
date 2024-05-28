package com.example.blog.domain.post.controller;

import com.example.blog.domain.post.dto.PostRequest;
import com.example.blog.domain.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    public ResponseEntity<?> create(@RequestBody PostRequest postRequest){
        try {
            postService.createPost(postRequest);
            return ResponseEntity.ok("게시글 생성");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
