package com.example.blog.domain.post.service;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostRes;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PostRes> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostRes::fromEntity)
                .collect(Collectors.toList());
    }

    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));
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

    @Transactional
    public int updateView(Long id) {
        return postRepository.updateView(id);
    }

    public Post searchWriter(String username) {
        Post searchPost = postRepository.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));

        return searchPost;
    }
    public Post searchTitle(String title) {
        Post searchPost = postRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));

        return searchPost;
    }

    public Post searchTitleOrContent(String title, String content) {
        Post searchPost = postRepository.findByTitleOrContent(title, content)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));

        return searchPost;
    }
}
