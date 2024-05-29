package com.example.blog.domain.post.controller;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostCreateRequestDto;
import com.example.blog.domain.post.dto.PostReadResponseDto;
import com.example.blog.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostReadResponseDto> read(@PathVariable Long id){
        PostReadResponseDto response = postService.read(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PostCreateRequestDto request){
        Post response = postService.create(request.writerId(), request);
        return ResponseEntity.ok().body(response.getId());
    }
}
