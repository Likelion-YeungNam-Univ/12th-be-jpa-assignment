package com.example.blog.domain.comment.service;

import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.board.repository.BoardRepository;
import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

//    public void createComment(Long userId, Long boardId, Comment comment) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalStateException("user does not exist."));
//
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new IllegalStateException("board does not exist."));
//
//        user.getUserComments().add(comment);
//        board.getBlog().getBlogComments().add(comment);
//        board.getBoardComments().add(comment);
//
//        commentRepository.save(comment);
//    }

    public void createComment(String nickname, String boardTitle, Comment comment) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalStateException("user does not exist."));

        Board board = boardRepository.findByTitle(boardTitle)
                .orElseThrow(() -> new IllegalStateException("board does not exist."));

        user.getUserComments().add(comment);
        board.getBlog().getBlogComments().add(comment);
        board.getBoardComments().add(comment);

        commentRepository.save(comment);
    }
}
