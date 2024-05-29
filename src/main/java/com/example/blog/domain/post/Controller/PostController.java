package com.example.blog.domain.post.Controller;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.service.PostService;
import com.example.blog.domain.post.dto.PostReq;
import com.example.blog.domain.post.dto.PostRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostRes>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostRes> postResponses = posts.stream()
                .map(PostRes::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(postResponses);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        Post post = postService.getPost(postId);
        PostRes postRes = PostRes.fromEntity(post);
        return ResponseEntity.ok(postRes);
    }

    @PostMapping("")
    public ResponseEntity<?> createPost(@RequestBody PostReq postReq) {
        Post post = Post.builder()
                .title(postReq.title())
                .content(postReq.content())
                .build();
        Post createdPost = postService.createPost(postReq.userId(), post);
        PostRes postRes = PostRes.fromEntity(createdPost);
        return ResponseEntity.ok(postRes);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody PostReq postReq) {
        Post post = Post.builder()
                .title(postReq.title())
                .content(postReq.content())
                .build();
        Post updatedPost = postService.updatePost(postId, post);
        PostRes postRes = PostRes.fromEntity(updatedPost);
        return ResponseEntity.ok(postRes);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}