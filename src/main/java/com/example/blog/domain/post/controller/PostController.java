package com.example.blog.domain.post.controller;

import com.example.blog.domain.post.dto.PostRequest;
import com.example.blog.domain.post.dto.PostResponse;
import com.example.blog.domain.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<PostResponse> posts = postService.getAll();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody PostRequest postRequest){
        try {
            PostResponse postResponse = postService.create(postRequest);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(String.format("http://localhost:8080/post/%s", postResponse.postId())));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> get(@PathVariable Long postId){
        try {
            PostResponse postResponse = postService.get(postId);
            return ResponseEntity.ok(postResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> update(@PathVariable Long postId, @RequestBody PostRequest postRequest){
        try{
            PostResponse postResponse = postService.update(postId, postRequest);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(String.format("http://localhost:8080/post/%s", postId)));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long postId){
        postService.delete(postId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/post"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
