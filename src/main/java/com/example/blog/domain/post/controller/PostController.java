package com.example.blog.domain.post.controller;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostListResponse;
import com.example.blog.domain.post.dto.PostRequest;
import com.example.blog.domain.post.dto.PostResponse;
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

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PostRequest request){
        Post response = postService.create(request);
        return ResponseEntity.ok().body(response.getId());
    }

    @GetMapping("")
    public ResponseEntity<List<PostListResponse>> readAll(){
        List<PostListResponse> response = postService.readAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> read(@PathVariable Long id){
        PostResponse response = postService.read(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                       @RequestBody PostRequest request){
        postService.update(id, request);
        return ResponseEntity.ok().body("수정이 완료되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @RequestBody PostRequest request){
        postService.delete(id, request);
        return ResponseEntity.ok().body("삭제가 완료되었습니다.");
    }
}
