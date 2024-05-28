package com.example.blog.domain.post.service;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostReq;
import com.example.blog.domain.post.dto.PostRes;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostRes createPost(Long userId, PostReq postReq) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        Post post = postReq.toPostEntity();
        post.setUser(user);

        return new PostRes(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public PostRes getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));
        return new PostRes(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public PostRes updatePost(Long postId, PostReq postReq) {
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));

        foundPost.update(postReq.getTitle(), postReq.getContent());
        return new PostRes(foundPost);
    }

    /* 게시글 조회수 증가 */
    @Transactional
    public void updateView(Long postId) {

        postRepository.updateView(postId);
    }

    /* 전체 게시글 불러오기 */
    @Transactional(readOnly = true)
    public List<PostRes> getAllPost() {

        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostRes::new).collect(Collectors.toList());
    }

    /* 제목으로 게시글 검색 */
    @Transactional(readOnly = true)
    public List<PostRes> readPostByTitle(String title) {

        List<Post> posts = postRepository.findByTitleContaining(title);
        return posts.stream().map(PostRes::new).collect(Collectors.toList());
    }

    /* 내용으로 게시글 검색 */
    @Transactional(readOnly = true)
    public List<PostRes> readPostByContent(String content) {

        List<Post> posts = postRepository.findByContentContaining(content);
        return posts.stream().map(PostRes::new).collect(Collectors.toList());
    }

    /* 제목으로 게시글 검색 */
    @Transactional(readOnly = true)
    public List<PostRes> readPostByUser(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(PostRes::new).collect(Collectors.toList());
    }
}
