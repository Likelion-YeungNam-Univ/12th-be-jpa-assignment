package com.example.blog.domain.board.service;

import com.example.blog.domain.blog.repository.BlogRepository;
import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class BoardServiceTest {

    @MockBean
    private BoardRepository boardRepository;

    @MockBean
    private BlogRepository blogRepository;

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("Board Service - 게시글 생성 테스트 ")
    void 게시글_생성_성공() {
        // given
        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        // when
        boardService.createBoard();


        // then


    }

}