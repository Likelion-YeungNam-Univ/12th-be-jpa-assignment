package com.example.blog.domain.like.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.like.domain.Like;
import com.example.blog.domain.like.repository.LikeRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import com.example.blog.handler.exceptionHandler.error.ErrorCode;
import com.example.blog.handler.exceptionHandler.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    /* 가게 좋아요 실행 메서드 */
    @Transactional
    public void likeRestaurant(Long userId, Long commentId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if(likeRepository.existsByUserAndComment(user, comment)) {
            throw new CustomException(ErrorCode.ALREADY_LIKE_COMMENT);
        }

        Like like = Like.builder()
                .user(user)
                .comment(comment)
                .build();

        likeRepository.save(like);
    }

    /* 가게 좋아요 취소 메서드 */
    @Transactional
    public void unLikeRestaurant(Long userId, Long commentId) {

        Like like = likeRepository.findByUser_IdAndComment_Id(userId, commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.LIKE_NOT_FOUND));

        likeRepository.delete(like);
    }
}
