package com.example.blog.domain.like.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.like.domain.Like;
import com.example.blog.domain.like.repository.LikeRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
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
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));

        if(likeRepository.existsByUserAndComment(user, comment)) {
            throw new RuntimeException("해당 댓글에 좋아요를 누른 적이 있습니다.");
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
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요 없음!"));

        likeRepository.delete(like);
    }
}
