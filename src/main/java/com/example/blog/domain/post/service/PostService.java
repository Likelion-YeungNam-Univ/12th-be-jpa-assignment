package com.example.blog.domain.post.service;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostListResponse;
import com.example.blog.domain.post.dto.PostRequest;
import com.example.blog.domain.post.dto.PostResponse;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Post findByPostId(Long postId){
        log.info("SELECT : Post include proxy");
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public Post findFetchByPostId(Long postId){
        log.info("SELECT : Post, User, Comment");
        return postRepository.findFetchById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    @Transactional
    public PostResponse read(Long postId){
        log.info("게시글 출력(게시물-사용자-댓글)");
        Post readPost = findFetchByPostId(postId);
        readPost.increaseViewCount();
        log.info("UPDATE: viewCount");
        return PostResponse.fromEntity(readPost);
    }

    @Transactional(readOnly = true)
    public List<PostListResponse> readAll(){
        log.info("게시글 목록 출력");
        log.info("SELECT : Post");
        return postRepository.findAll().stream()
                .map(PostListResponse::fromEntity).toList();
    }

    @Transactional
    public Post create(PostRequest request) {
        log.info("게시글 생성");
        User user = userService.findById(request.userId());
        Post createPost = request.toEntity(user);
        createPost.setUser(user);
        log.info("INSERT : Post");
        return postRepository.save(createPost);
    }

    @Transactional
    public void update(Long postId, PostRequest request) {
        log.info("게시글 수정");
        Post foundPost = verifyPostAuthor(postId, request.userId());
        foundPost.update(request.title(), request.content());
        log.info("UPDATE : title, content");
    }

    @Transactional
    public void delete(Long postId, PostRequest request) {
        log.info("게시글 삭제");
        Post foundPost = verifyPostAuthor(postId, request.userId());
        postRepository.delete(foundPost);
        log.info("DELETE : Post, cascade Comment");
    }

    @Transactional(readOnly = true)
    public Post verifyPostAuthor(Long postId, Long userId){
        log.info("게시글 작성자 검사");
        Post post = findByPostId(postId);
        if(!post.getUser().getId().equals(userId))
            throw new IllegalArgumentException("해당 게시물의 작성자가 아닙니다.");
        return post;
    }
}
