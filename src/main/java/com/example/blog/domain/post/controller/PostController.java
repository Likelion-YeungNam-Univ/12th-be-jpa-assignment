package com.example.blog.domain.post.controller;

import com.example.blog.domain.post.dto.PostRequest;
import com.example.blog.domain.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/search")
    public ResponseEntity<?> Search(@RequestParam(required = false) String title, @RequestParam(required = false) String content) {
        try {
            return ResponseEntity.ok(postService.search(title, content));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(postService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PostRequest postRequest){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postRequest));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> get(@PathVariable Long postId){
        try {
            return ResponseEntity.ok(postService.get(postId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> update(@PathVariable Long postId, @RequestBody PostRequest postRequest){
        try{
            return ResponseEntity.ok(postService.update(postId, postRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> delete(@PathVariable Long postId){
        postService.delete(postId);
        return ResponseEntity.ok("게시물 삭제완료");
    }
}
