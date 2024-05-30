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

    /**
     * 좋아요 또는 좋아요 취소 기능을 수행한다.
     * @param postId 게시글 id
     * @param commentId 댓글 id
     * */
    @Transactional
    public CommentResponse likeOrUnlike(Long postId, Long commentId) {
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId).orElseThrow(()-> new IllegalArgumentException("해당 댓글 없음"));
        Long userId = comment.getUser().getId();
        Likes likes = likeRepository.findByPostIdAndCommentIdAndUserId(postId, commentId, userId)
                .orElse(null);
        if (likes == null) {
            return like(comment);
        }
        return unlike(comment, likes);
    }

    /**
     * 좋아요를 취소한다.
     * @param comment 댓글 엔티티
     * @param likes 좋아요 엔티티
     * */
    private CommentResponse unlike(Comment comment, Likes likes) {
        likeRepository.delete(likes);
        comment.unlike();
        commentRepository.save(comment);
        return CommentResponse.fromEntity(comment);
    }

    /**
     * 좋아요를 취소한다.
     * @param comment 댓글 엔티티
     * */
    public CommentResponse like(Comment comment){
        likeRepository.save(Likes.of(comment.getUser(), comment.getPost(), comment));
        comment.like();
        commentRepository.save(comment);
        return CommentResponse.fromEntity(comment);
    }
}
