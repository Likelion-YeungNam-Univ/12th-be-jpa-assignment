package com.example.blog.domain.post.controller;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostReq;
import com.example.blog.domain.post.dto.PostRes;
import com.example.blog.domain.post.service.PostService;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserReq;
import com.example.blog.domain.user.dto.UserRes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private  final PostService postService;


    @PostMapping("")
    public ResponseEntity<PostRes> createPost(@RequestBody PostReq postReq) {
        Post post = postReq.toEntity();
        Post createdPost = postService.createPost(postReq.userId(), post);
        PostRes postRes = new PostRes(createdPost.getId(), createdPost.getTitle(), createdPost.getContent(), createdPost.getUser().getId());
        return ResponseEntity.ok(postRes);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostRes> getPost(@PathVariable Long postId) {
        Post post = postService.getPost(postId);
        PostRes postRes = new PostRes(post.getId(), post.getTitle(), post.getContent(), post.getUser().getId());
        return ResponseEntity.ok(postRes);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostRes> updatePost(@PathVariable Long postId, @RequestBody PostReq postReq) {
        Post post = postReq.toEntity();
        Post updatedPost = postService.updatePost(postId, post);
        PostRes postRes = new PostRes(updatedPost.getId(), updatedPost.getTitle(), updatedPost.getContent(), updatedPost.getUser().getId());
        return ResponseEntity.ok(postRes);
    }

}
