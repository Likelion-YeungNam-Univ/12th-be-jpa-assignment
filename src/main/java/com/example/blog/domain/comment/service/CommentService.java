package com.example.blog.domain.comment.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentRequest;
import com.example.blog.domain.comment.dto.CommentResponse;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentResponse create(Long postId, CommentRequest commentRequest) {

        return null;
    }
    /**
     * 댓글을 전부 얻어온다.
     * @param postId 게시글아이디
     */
    public List<CommentResponse> getAll(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId).orElseThrow(() -> new IllegalArgumentException("접근 오류"));
        return comments.stream()
                .map(CommentResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public CommentResponse get(Long postId, Long commentId) {
    }

    public CommentResponse update(Long postId, Long commentId) {
    }

    public void delete(Long postId, Long commentId) {
    }
}
