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

    // blogId값으로 블로그의 게시글 삭제
//    public Board createBoard(Long blogId, Board board) {
//        Blog blog = blogRepository.findById(blogId)
//                .orElseThrow(() -> new IllegalStateException("blog does not exist"));
//
//        board.setBlog(blog);
//        blog.getBoards().add(board);
//
//        return boardRepository.save(board);
//    }

    // blog 이름으로 블로그 게시글 삭제
    public Board createBoard(String name, Board board) {
        Blog blog = blogRepository.findByName(name)
                .orElseThrow(() -> new IllegalStateException("blog does not exist"));

        board.setBlog(blog);
        blog.getBoards().add(board);

        return boardRepository.save(board);
    }

    public Board getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalStateException("board does not exist"));

        return board;
    }
// boardId 값으로 수정
//    public Board updateBoard(Long boardId, Board board) {
//        Board newBoard = boardRepository.findById(boardId)
//                .orElseThrow(() -> new IllegalStateException("board does not exist"));
//
//        newBoard.updateTitle(board.getTitle());
//        newBoard.updateContent(board.getContent());
//
//        return newBoard;
//    }

    public void updateBoard(String title, Board board) {
        Board prevBoard = boardRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalStateException("board does not exist"));

        Blog blog = prevBoard.getBlog();

        blog.getBoards().remove(prevBoard);
        blog.getBoards().add(board);

        prevBoard.updateTitle(board.getTitle());
        prevBoard.updateContent(board.getContent());
        this.boardRepository.save(board);
    }

// boardId값으로 삭제
//    public void deleteBoard(Long boardId) {
//        Board board = boardRepository.findById(boardId)
//                .orElseThrow(() -> new IllegalStateException("board does not exist"));
//
//        // 게시글로부터 해당 게시글이 작성되어 있는 블로그를 복사
//        // 해당 블로그 게시글들에서 삭제할 게시글 삭제.
//        Blog blog = board.getBlog();
//        blog.getBoards().remove(board);
//
//        boardRepository.deleteById(boardId);
//    }

    // board Title로 삭제
    public void deleteBoard(String title) {
        Board board = boardRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalStateException("board does not exist"));

        // 게시글로부터 해당 게시글이 작성되어 있는 블로그를 복사
        // 해당 블로그 게시글들에서 삭제할 게시글 삭제.
        Blog blog = board.getBlog();
        blog.getBoards().remove(board);

//        boardRepository.deleteById(board.getId());
        boardRepository.deleteByTitle(board.getTitle());
    }

    public void readBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalStateException("board does not exist"));

        board.increaseView();
    }
}
