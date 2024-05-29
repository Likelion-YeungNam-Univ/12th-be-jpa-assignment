package com.example.blog.domain.post.service;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(Long userId, Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        post.setUser(user);
        return postRepository.save(post);
    }

    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));

        // 조회수 증가 로직 추가
        post.incrementViewCount();
        postRepository.save(post);

        return post;
    }


    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post updatePost(Long postId, Post post) {
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));

        foundPost.update(post.getTitle(), post.getContent());
        return foundPost;
    }

    public Optional<Post> searchPostByTitle(String title) {
        Optional<Post> postOptional = postRepository.findByTitle(title);
        postOptional.ifPresent(post -> {
            post.incrementViewCount();
            postRepository.save(post);
        });
        return postOptional;
    }

    public Optional<Post> searchPostByTitleOrContent(String title, String content) {
        Optional<Post> postOptional = postRepository.findByTitleOrContent(title, content);
        postOptional.ifPresent(post -> {
            post.incrementViewCount();
            postRepository.save(post);
        });
        return postOptional;
    }


}