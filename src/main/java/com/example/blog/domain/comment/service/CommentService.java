package com.example.blog.domain.comment.service;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.comment.dto.CommentListResponse;
import com.example.blog.domain.comment.dto.CommentRequest;
import com.example.blog.domain.comment.dto.CommentUpdateRequest;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.service.PostService;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public Comment findById(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));
    }

    @Transactional
    public Comment create(CommentRequest request){
        User user = userService.findById(request.userId());
        Post post = postService.findByPostId(request.postId());
        Comment createComment = request.toEntity(user, post);
        createComment.setUser(user);
        createComment.setPost(post);

        return createComment;
    }

    @Transactional
    public Comment update(Long id, CommentUpdateRequest request){
        Comment fountComment = findById(id);
        if(!fountComment.getUser().getId().equals(request.userId()))
            throw new IllegalArgumentException("댓글의 작성자가 아닙니다.");
        fountComment.update(request.content());
        return fountComment;
    }
}
