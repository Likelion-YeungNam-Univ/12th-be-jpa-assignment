package com.example.blog.domain.comment.service;

import com.example.blog.domain.comment.dto.CommentRequest;
import com.example.blog.domain.comment.dto.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    public CommentResponse create(CommentRequest commentRequest) {
        return null;
    }

    public List<CommentResponse> getAll() {
    }

    public CommentResponse get(Long postId, Long commentId) {
    }

    public CommentResponse update(Long postId, Long commentId) {
    }
}
