package com.example.blog.domain.post.controller;

import com.example.blog.domain.post.dto.PostReq;
import com.example.blog.domain.post.dto.PostRes;
import com.example.blog.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    /* 글 생성하기 */
    @PostMapping("/{userId}")
    public ResponseEntity<?> savePost(@PathVariable Long userId,
                                      @RequestBody PostReq postReq) {

        return ResponseEntity.ok(postService.createPost(userId, postReq));
    }

    /* 글 불러오기 */
    @GetMapping("/{postId}")
    public PostRes readPost(@PathVariable Long postId) {

        postService.updateView(postId);     // 게시글 조회수 증가
        return postService.getPost(postId);
    }

    /* 글 검색하기 */
    @GetMapping
    public List<PostRes> readPost(@RequestParam(required = false, value = "type") String type,
                                  @RequestParam(required = false, value = "search-keyword") String searchKeyword) {

        if (type == null)
            return postService.getAllPost();

        return switch (type) {
            case "title" -> postService.readPostByTitle(searchKeyword);
            case "content" -> postService.readPostByContent(searchKeyword);
            case "user" ->  postService.readPostByUser(searchKeyword);
            default -> throw new IllegalArgumentException("잘못된 타입 입력");
        };
    }

    /* 글 수정하기 */
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId,
                                        @RequestBody PostReq postReq) {

        return ResponseEntity.ok(postService.updatePost(postId, postReq));
    }

    /* 글 삭제하기 */
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);
        return ResponseEntity.ok("글 삭제 성공.");
    }
}
