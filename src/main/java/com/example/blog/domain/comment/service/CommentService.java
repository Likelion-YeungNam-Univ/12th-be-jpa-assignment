package com.example.blog.domain.comment.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentReq;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommentRes createComment(CommentReq commentReq) {
        Post post = postRepository.findById(commentReq.getPostId()).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
        User user = userRepository.findById(commentReq.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Comment comment = Comment.builder().post(post).user(user).content(commentReq.getContent()).build();
        return CommentRes.fromEntity(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public List<CommentRes> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(CommentRes::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentRes> getCommentsByUserId(Long userId) {
        List<Comment> comments = commentRepository.findByUserId(userId);
        return comments.stream().map(CommentRes::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public CommentRes updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));
        comment.update(content);
        return CommentRes.fromEntity(commentRepository.save(comment));
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));
        commentRepository.delete(comment);
    }
}
