package com.example.blog.domain.board.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardTest {

    // Entity 관련 부분이라서 따로 Assertions를 활용하지 않고 단순 전후 비교만 진행.
    @Test
    @DisplayName("Board Entity Builder Test")
    void 게시글_등록_테스트() {
        // given & when
        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        // then
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @Test
    @DisplayName("Board Title Change Test")
    void 게시글_제목_수정_테스트() {
        // given & when
        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        board.updateTitle("change Test Title");
        // then
        System.out.println(board.getTitle());
    }

    @Test
    @DisplayName("Board Content Change Test")
    void 게시글_내용_수정_테스트() {
        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        board.updateContent("change Test Content");

        System.out.println(board.getContent());
    }

    @Test
    @DisplayName("조회수 증가 테스트")
    void 조회수_증가_테스트() {
        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        System.out.println(board.getViewCount());
        board.increaseView();
        System.out.println(board.getViewCount());
    }
}