package com.example.blog.domain.comment.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentReq;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostRes;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment createComment(Long postId, CommentReq commentReq) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물 없음!"));

        User user = userRepository.findById(commentReq.userId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        Comment comment = commentReq.toEntity();
        comment.setPost(post);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    public List<CommentRes> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentRes::fromEntity)
                .collect(Collectors.toList());
    }

    public Comment getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));
        return comment;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(Long commentId, Comment comment) {
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));

        foundComment.update(comment.getContent());
        return comment;
    }

    public List<Comment> getCommentsByPost(Long postId){
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return commentRepository.findByPost(post.get());
        }
        return Collections.emptyList();
    }

}
