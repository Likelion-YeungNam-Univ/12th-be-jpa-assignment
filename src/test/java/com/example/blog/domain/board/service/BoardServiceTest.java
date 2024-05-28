package com.example.blog.domain.board.service;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.blog.repository.BlogRepository;
import com.example.blog.domain.blog.service.BlogService;
import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.board.repository.BoardRepository;
import com.example.blog.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BoardServiceTest {

    @MockBean
    private BoardRepository boardRepository;

    @MockBean
    private BlogRepository blogRepository;

    @MockBean
    private BlogService blogService;

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

        User user = User.builder()
                .email("test@test1.com")
                .password("test")
                .nickname("test1")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        Blog blog = Blog.builder()
                .user(user)
                .name(user.getNickname())
                .build();

        // when
        // 1. 블로그 생성 후 리포지토리에 저장시킴.
        // 2. 게시글 생성 후 블로그에 저장시킴
        // 연관 관계로 인해서 같이 만들어주어야 함.
        blogService.createBlog(blog);
        when(blogRepository.findByName(blog.getName())).thenReturn(Optional.of(blog));

        boardService.createBoard(blog.getName(), board);
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));

        // then
        // 정상적으로 BoardRepository에 게시글이 생성된다면 제목으로 게시글 찾아서 처음 builder로 생성한 객체와 동일한지 확인.
        assertThat(board).isEqualTo(boardRepository.findByTitle(board.getTitle()).get());
    }

    @Test
    @DisplayName("Board Service - 게시글 삭제 테스트 ")
    void 게시글_삭제_성공() {
        // given
        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        User user = User.builder()
                .email("test@test1.com")
                .password("test")
                .nickname("test1")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        Blog blog = Blog.builder()
                .user(user)
                .name(user.getNickname())
                .build();

        // when
        // 1. 블로그 생성 후 리포지토리에 저장시킴.
        // 2. 게시글 생성 후 블로그에 저장시킴
        // 3. 게시글 찾은 후 deleteBoard 메서드로 삭제
        // 연관 관계로 인해서 같이 만들어주어야 함.
        blogService.createBlog(blog);
        when(blogRepository.findByName(blog.getName())).thenReturn(Optional.of(blog));

        boardService.createBoard(blog.getName(), board);
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));

        // then
        // verify()를 사용 - deleteBoard 메소드를 호출하고 해당 메소드내에서 boardRepository.deleteByTitle() 메소드를 실행하는지 검증
        boardService.deleteBoard(board.getTitle());
        verify(boardRepository).deleteByTitle(board.getTitle());
    }

    @Test
    @DisplayName("Board Service - 게시글 수정 테스트")
    void 게시글_수정_성공() {
        // given
        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        Board newBoard = Board.builder()
                .title("Test updated Title")
                .content("Test updated Content")
                .build();

        User user = User.builder()
                .email("test@test1.com")
                .password("test")
                .nickname("test1")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        Blog blog = Blog.builder()
                .user(user)
                .name(user.getNickname())
                .build();

        // when
        // 1. 블로그 생성 후 리포지토리에 저장시킴.
        // 2. 게시글 생성 후 블로그에 저장시킴
        // 3. 게시글 찾은 후 deleteBoard 메서드로 삭제
        // 연관 관계로 인해서 같이 만들어주어야 함.
        blogService.createBlog(blog);
        when(blogRepository.findByName(blog.getName())).thenReturn(Optional.of(blog));

        boardService.createBoard(blog.getName(), board);
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));

        // then
        boardService.updateBoard(board.getTitle(), newBoard);
        assertThat(newBoard).isEqualTo(boardRepository.findByTitle(newBoard.getTitle()).get());
    }
}