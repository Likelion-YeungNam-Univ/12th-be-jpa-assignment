package com.example.blog.domain.like.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.like.domain.CommentLike;
import com.example.blog.domain.like.dto.LikeReq;
import com.example.blog.domain.like.repository.LikeRepository;

import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository LikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public void toggleLike(LikeReq likeReq) {
        User user = userRepository.findById(likeReq.userId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));
        Comment comment = commentRepository.findById(likeReq.commentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));

        Optional<CommentLike> likes = LikeRepository.findByUserAndComment(user, comment);

        if(likes.isPresent()){
            //이미 좋아요함 -> 취소
            LikeRepository.delete(likes.get());
        }
        else{
            CommentLike commentLike = new CommentLike(user, comment);
            LikeRepository.save(commentLike);
        }
    }


}
