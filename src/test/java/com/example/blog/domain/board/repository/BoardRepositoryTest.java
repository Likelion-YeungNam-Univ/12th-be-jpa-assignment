package com.example.blog.domain.board.repository;

import com.example.blog.domain.board.domain.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class BoardRepositoryTest {

    @MockBean
    private BoardRepository boardRepository;

    @Test
    @DisplayName("제목으로 게시글 찾기")
    void 제목으로_게시글_찾기() {
        // given
        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        // when
        boardRepository.save(board);
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));

        // then
        assertThat(board).isEqualTo(boardRepository.findByTitle(board.getTitle()).get());
    }

    @Test
    @DisplayName("내용으로 게시글 찾기")
    void 내용으로_게시글_찾기() {
        // given
        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        // when
        boardRepository.save(board);
        when(boardRepository.findByContent(board.getContent())).thenReturn(Optional.of(board));

        // then
        assertThat(board).isEqualTo(boardRepository.findByContent(board.getContent()).get());
    }
}