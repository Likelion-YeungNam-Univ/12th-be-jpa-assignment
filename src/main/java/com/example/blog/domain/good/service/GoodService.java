package com.example.blog.domain.good.service;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.good.domain.Good;
import com.example.blog.domain.good.repository.GoodRepository;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final GoodRepository goodRepository;


    public Good createGood(Long userId, Long commentId, Good good) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));


        good.setUser(user);
        good.setComment(comment);

        return goodRepository.save(good);
    }

    public Good updateGood(Long goodId, Good good) {

        Good foundGood = goodRepository.findById(goodId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));

        foundGood.update(good.isLikeCheck());
        return goodRepository.save(foundGood);
    }

    public int countLikesByCommentId(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        return (int) comment.getLikes().stream().filter(Good::isLikeCheck).count();
    }

}
