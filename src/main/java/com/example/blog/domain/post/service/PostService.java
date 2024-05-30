package com.example.blog.domain.post.service;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostRes;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void updateView(Long postId) {
        Optional<Post> posts = postRepository.findById(postId);
        if (posts.isPresent()) {
            Post post = posts.get();
            post.setView(post.getView() + 1);
            postRepository.save(post);
        }
    }

    public List<Post> findByUserUsername(String username) {
        return postRepository.findByUserUsername(username);
    }
    public List<Post> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public List<Post> findByTitleOrContent(String title, String content) {
        return postRepository.findByTitleOrContent(title, content);
    }
    public List<Post> searchPost(String keyword) {
        List <Post> posts = findByUserUsername(keyword);
        if (!posts.isEmpty()) return posts;

        posts = findByTitle(keyword);
        if (!posts.isEmpty()) return posts;

        return findByTitleOrContent(keyword, keyword);
    }
}
