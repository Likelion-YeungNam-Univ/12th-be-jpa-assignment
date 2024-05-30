package com.example.blog.domain.post.Controller;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.service.PostService;
import com.example.blog.domain.post.dto.PostReq;
import com.example.blog.domain.post.dto.PostRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
        postService.updateView(postId); // 조회수 증가
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
    @RestController
    public class SearchController {

        // 검색 기능 : 제목
        @GetMapping("/search/title")
        public ResponseEntity<?> searchByTitle(@RequestParam String titleKeyword) {
            List<Post> searchList = postService.searchByTitle(titleKeyword);
            if (searchList.isEmpty()) {
                return ResponseEntity.status(404).body("해당 게시물 없음!");
            }
            List<PostRes> postResponses = searchList.stream()
                    .map(PostRes::fromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(postResponses);
        }

        // 검색 기능 : 내용
        @GetMapping("/search/content")
        public ResponseEntity<?> searchByContent(@RequestParam String contentKeyword) {
            List<Post> searchList = postService.searchByContent(contentKeyword);
            if (searchList.isEmpty()) {
                return ResponseEntity.status(404).body("해당 내용 없음!");
            }
            List<PostRes> postResponses = searchList.stream()
                    .map(PostRes::fromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(postResponses);
        }

        // 검색 기능 : 작성자
        @GetMapping("/search/username")
        public ResponseEntity<?> searchByUsername(@RequestParam String usernameKeyword) {
            List<Post> searchList = postService.searchByUsername(usernameKeyword);
            if (searchList.isEmpty()) {
                return ResponseEntity.status(404).body("해당 작성자 없음!");
            }
            List<PostRes> postResponses = searchList.stream()
                    .map(PostRes::fromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(postResponses);
        }
    }

}