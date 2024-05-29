package com.example.blog.domain.post.controller;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostListResponseDto;
import com.example.blog.domain.post.dto.PostRequestDto;
import com.example.blog.domain.post.dto.PostReadResponseDto;
import com.example.blog.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<PostListResponseDto>> readAll(){
        List<PostListResponseDto> response = postService.readAll();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PostRequestDto request){
        Post response = postService.create(request);
        return ResponseEntity.ok().body(response.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                       @RequestBody PostRequestDto request){
        postService.update(id, request);
        return ResponseEntity.ok().body("수정이 완료되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @RequestBody PostRequestDto request){
        postService.delete(id, request);
        return ResponseEntity.ok().body("삭제가 완료되었습니다.");
    }
}
