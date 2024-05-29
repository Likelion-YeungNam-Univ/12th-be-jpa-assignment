package com.example.blog.domain.post.service;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostRequestDto;
import com.example.blog.domain.post.dto.PostReadResponseDto;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import com.example.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public Post findByPostId(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));
    }

    public PostReadResponseDto read(Long postId){
        Post readPost = findByPostId(postId);
        readPost.increaseViewCount();
        return PostReadResponseDto.fromEntity(readPost);
    }

    public Post create(PostRequestDto request) {
        User user = userService.findById(request.userId());
        Post createPost = request.toEntity(user);
        createPost.setUser(user);
        return postRepository.save(createPost);
    }

    public Post update(Long postId, PostRequestDto request) {
        Post foundPost = findByPostId(postId);
        isWriter(request.userId(), foundPost);
        foundPost.update(request.title(), request.content());
        return foundPost;
    }

    public void isWriter(Long userId, Post post){
        User user = userService.findById(userId);
        if(!user.getId().equals(post.getUser().getId()))
            throw new IllegalArgumentException("해당 게시물의 작성자가 아닙니다.");
    }

    public void delete(Long postId, PostRequestDto request) {
        Post foundPost = findByPostId(postId);
        isWriter(request.userId(), foundPost);
        postRepository.delete(foundPost);
    }
}
