package com.example.blog.domain.post.controller;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostReq;
import com.example.blog.domain.post.dto.PostRes;
import com.example.blog.domain.post.service.PostService;

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
    private PostService postService;

    @PostMapping("")
    public ResponseEntity<?> createPost(@RequestBody PostReq postReq){
        Post post = postService.createPost(postReq.userId(), postReq.toEntity());
        PostRes postRes = PostRes.fromEntity(post);
        return ResponseEntity.ok(postRes);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllPosts() {
        List <PostRes> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        //조회수 증가
        postService.updateView(id);
        PostRes postRes= PostRes.fromEntity(post);
        return ResponseEntity.ok(postRes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostReq postReq) {
        Post updatedPost = postService.updatePost(id, postReq.toEntity());
        PostRes postRes = PostRes.fromEntity(updatedPost);
        return ResponseEntity.ok(postRes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    //사용자 검색
    @GetMapping("/{username}")
    public ResponseEntity<?> searchWriter(@PathVariable String username){
        Post post = postService.searchWriter(username);
        //조회수 증가
        postService.updateView(post.getId());

        PostRes postRes= PostRes.fromEntity(post);
        return ResponseEntity.ok(postRes);
    }

    //제목 검색
    @GetMapping("/{title}")
    public ResponseEntity<?> searchTitle(@PathVariable String title){
        Post post = postService.searchTitle(title);
        //조회수 증가
        postService.updateView(post.getId());
        PostRes postRes= PostRes.fromEntity(post);
        return ResponseEntity.ok(postRes);
    }

    //제목 or 내용 검색
    @GetMapping("/{content}")
    public ResponseEntity<?> searchTitleOrContent(@PathVariable String title, @PathVariable String content){
        Post post = postService.searchTitleOrContent(title,content);
        //조회수 증가
        postService.updateView(post.getId());
        PostRes postRes= PostRes.fromEntity(post);
        return ResponseEntity.ok(postRes);
    }

}
