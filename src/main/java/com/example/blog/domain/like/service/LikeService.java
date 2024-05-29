package com.example.blog.domain.like.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentResponse;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.like.domain.Like;
import com.example.blog.domain.like.repository.LikeRepository;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private CommentRepository commentRepository;

    public CommentResponse likeOrUnlike(Long postId, Long commentId) {
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId).orElseThrow(()-> new IllegalArgumentException("검색 오류"));
        Long userId = comment.getUser().getId();
        Like like = likeRepository.findByPostIdAndCommentIdAndUserId(postId, commentId, userId)
                .orElse(null);
        if (like == null) {
            likeRepository.save(Like.of(comment.getUser(), comment.getPost(), comment));
            comment.like();
            return CommentResponse.fromEntity(comment);
        }
        likeRepository.delete(like);
        comment.unlike();
        return CommentResponse.fromEntity(comment);
    }
}
