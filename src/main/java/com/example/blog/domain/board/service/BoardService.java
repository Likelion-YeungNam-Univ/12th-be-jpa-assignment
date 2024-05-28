package com.example.blog.domain.board.service;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.blog.repository.BlogRepository;
import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BlogRepository blogRepository;
    private final BoardRepository boardRepository;

    public Board createBoard(Long blogId, Board board) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalStateException("blog does not exist"));

        board.setBlog(blog);
        return boardRepository.save(board);
    }

    public Board getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalStateException("board does not exist"));

        return board;
    }

    public Board updateBoard(Long boardId, Board board) {
        Board prevBoard = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalStateException("board does not exist"));

        prevBoard.updateTitle(board.getTitle());
        prevBoard.updateContent(board.getContent());

        return prevBoard;
    }
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public void readBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalStateException("board does not exist"));

        board.increaseView();
    }
}
