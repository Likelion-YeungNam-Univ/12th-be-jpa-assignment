package com.example.blog.domain.like.service;

import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.board.repository.BoardRepository;
import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.like.domain.Like;
import com.example.blog.domain.like.repository.LikeRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void clickedLike(String userNickname, String boardTitle, Long commentId) {
        User user = userRepository.findByNickname(userNickname)
                .orElseThrow(() -> new IllegalStateException("user does not exist"));

        Board board = boardRepository.findByTitle(boardTitle)
                .orElseThrow(() -> new IllegalStateException("board does not exist"));

//        Comment comment = board.getBoardComments().get(0);
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("comment does not exist"));

        Like like = Like.builder()
                .user(user)
                .comment(comment)
                .build();

        board.getBoardComments().get(0).getLikes().add(like);
        user.addLike(like);
        comment.addLike(like);

        System.out.println(like.getComment().getId());
        likeRepository.save(like);
    }

    @Transactional
    public void removeLike(String userNickname, Long commentId) {
        User user = userRepository.findByNickname(userNickname)
                .orElseThrow(() -> new IllegalStateException("user does not exist"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("comment does not exist"));

        Optional<Like> userLike = likeRepository.findByUserNicknameAndCommentId(userNickname, commentId);

        if (userLike.isPresent()) {
            Like like = userLike.get();
            user.removeLike(like);
            comment.removeLike(like);
        } else {
            throw new IllegalStateException("like button didn't clicked");
        }
    }
}
