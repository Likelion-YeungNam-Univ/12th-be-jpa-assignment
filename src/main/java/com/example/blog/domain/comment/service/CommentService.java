package com.example.blog.domain.comment.service;

import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.board.repository.BoardRepository;
import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
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

//    public void deleteComment(Long userId, Long boardId, Comment comment) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalStateException("user does not exist."));
//
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new IllegalStateException("board does not exist."));
//
//        user.getUserComments().remove(comment);
//        board.getBlog().getBlogComments().remove(comment);
//        board.getBoardComments().remove(comment);
//    }

    @Transactional
    public void deleteComment(String nickname, String boardTitle, Comment comment) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalStateException("user does not exist."));

        Board board = boardRepository.findByTitle(boardTitle)
                .orElseThrow(() -> new IllegalStateException("board does not exist."));

        user.getUserComments().remove(comment);
        board.getBlog().getBlogComments().remove(comment);
        board.getBoardComments().remove(comment);

        commentRepository.delete(comment);
    }

//    public void updateComment(Long userId, Long boardId, Long commentId, Comment comment) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalStateException("user does not exist."));
//
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new IllegalStateException("board does not exist."));
//
//        Comment prevComment = commentRepository.findById(commentId)
//                        .orElseThrow(() -> new IllegalStateException(""))
//
//        user.getUserComments().remove(comment);
//        board.getBlog().getBlogComments().remove(comment);
//        board.getBoardComments().remove(comment);
//    }

    @Transactional
    public void updateComment(String nickname, String boardTitle, Long prevCommentId, Comment newComment) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalStateException("user does not exist."));

        Board board = boardRepository.findByTitle(boardTitle)
                .orElseThrow(() -> new IllegalStateException("board does not exist."));

        Comment prevComment = commentRepository.findById(prevCommentId)
                .orElseThrow(() -> new IllegalStateException("comment does not exist"));

//        Comment prevComment = commentRepository.findByContent(comment.getContent())
//                .orElseThrow(() -> new IllegalStateException("comment does not exist"));

        // 기존의 리스트에서 삭제
        user.getUserComments().remove(prevComment);
        board.getBlog().getBlogComments().remove(prevComment);
        board.getBoardComments().remove(prevComment);

        prevComment.updateContent(newComment.getContent());

        // 수정된 comment를 리스트에 추가해줌.
        user.getUserComments().add(prevComment);
        board.getBlog().getBlogComments().add(prevComment);
        board.getBoardComments().add(prevComment);

        commentRepository.save(prevComment);
    }

    @Transactional
    public List<Comment> getComments(String boardTitle) {
        Board board = boardRepository.findByTitle(boardTitle)
                .orElseThrow(() -> new IllegalStateException("board does not exist"));

        return board.getBoardComments();
    }

}