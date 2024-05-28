package com.example.blog.domain.post.service;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostRequest;
import com.example.blog.domain.post.dto.PostResponse;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.dto.UserResponse;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    /**
     * 게시글을 전부 얻어온다.
     */
    public List<PostResponse> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 게시글을 업로드한다.
     * @param postRequest 게시글 dto
     */
    public PostResponse create(PostRequest postRequest) {
        User user = userRepository.findById(postRequest.userId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 업로드 불가 : 유저접근오류"));
        Post post = postRequest.toEntity();
        post.setUser(user);
        postRepository.save(post);

        return PostResponse.fromEntity(post);
    }

    /**
     * 게시글을 얻어오고 조회수를 1 증가시킨다.
     * @param postId 게시글아이디
     */
    public PostResponse get(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));
        post.views();
        return PostResponse.fromEntity(post);
    }

    /**
     * 게시글을 삭제한다.
     * @param postId 게시글아이디
     */
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    /**
     * 게시글을 수정한다.
     * @param postId 게시글아이디
     */
    public PostResponse update(Long postId, PostRequest postRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));
        post.update(post.getTitle(), post.getContent());
        return PostResponse.fromEntity(post);
    }

}
