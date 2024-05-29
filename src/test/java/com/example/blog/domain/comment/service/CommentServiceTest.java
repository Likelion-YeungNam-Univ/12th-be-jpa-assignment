package com.example.blog.domain.comment.service;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.blog.repository.BlogRepository;
import com.example.blog.domain.blog.service.BlogService;
import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.board.repository.BoardRepository;
import com.example.blog.domain.board.service.BoardService;
import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class CommentServiceTest {

    @MockBean
    private BlogService blogService;

    @MockBean
    private BlogRepository blogRepository;

    @MockBean
    private BoardService boardService;

    @MockBean
    private BoardRepository boardRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("댓글 생성 테스트")
    void 댓글_생성_성공() {
        User user = User.builder()
                .email("test@test1.com")
                .password("test")
                .nickname("test1")
                .birthdate(LocalDate.parse("2024-05-27"))
                .build();

        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        Blog blog = Blog.builder()
                .user(user)
                .name(user.getNickname())
                .build();

        Comment comment = Comment.builder()
                .content("test comment")
                .build();

        blogService.createBlog(blog);
        when(blogRepository.findByName(blog.getName())).thenReturn(Optional.of(blog));

        boardService.createBoard(blog.getName(), board);
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));

        commentService.createComment(user.getNickname(), board.getTitle(), comment);

    }
}