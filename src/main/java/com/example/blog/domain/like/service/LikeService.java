package com.example.blog.domain.like.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentResponse;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.like.domain.Likes;
import com.example.blog.domain.like.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Transactional
    public CommentResponse likeOrUnlike(Long postId, Long commentId) {
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId).orElseThrow(()-> new IllegalArgumentException("검색 오류"));
        Long userId = comment.getUser().getId();
        Likes likes = likeRepository.findByPostIdAndCommentIdAndUserId(postId, commentId, userId)
                .orElse(null);
        if (likes == null) {
            return like(comment);
        }
        return unlike(comment, likes);
    }

    private CommentResponse unlike(Comment comment, Likes likes) {
        likeRepository.delete(likes);
        comment.unlike();
        commentRepository.save(comment);
        return CommentResponse.fromEntity(comment);
    }

    public CommentResponse like(Comment comment){
        likeRepository.save(Likes.of(comment.getUser(), comment.getPost(), comment));
        comment.like();
        commentRepository.save(comment);
        return CommentResponse.fromEntity(comment);
    }
}
