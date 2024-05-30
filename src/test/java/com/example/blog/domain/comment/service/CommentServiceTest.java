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
import com.example.blog.domain.user.repository.UserRepository;
import com.example.blog.domain.user.service.UserService;
import jakarta.transaction.Transactional;
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
class CommentServiceTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

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
    @Transactional
    void 댓글_생성_성공() {
        // given
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

        // when
        user.getUserComments().add(comment);
        board.setBlog(blog);

        userService.userSignUp(user);
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));

        blogService.createBlog(blog);
        when(blogRepository.findByName(blog.getName())).thenReturn(Optional.of(blog));

        boardService.createBoard(blog.getName(), board);
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));

        // then
        commentService.createComment(user.getNickname(), board.getTitle(), comment);
        assertThat(board.getBoardComments()).contains(comment);
        assertThat(blog.getBoards()).contains(board);
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    @Transactional
    void 댓글_삭제_테스트() {
        // given
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

        // when
        user.getUserComments().add(comment);
        board.setBlog(blog);

        userService.userSignUp(user);
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));

        blogService.createBlog(blog);
        when(blogRepository.findByName(blog.getName())).thenReturn(Optional.of(blog));

        boardService.createBoard(blog.getName(), board);
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));

        commentService.createComment(user.getNickname(), board.getTitle(), comment);
        assertThat(board.getBoardComments()).contains(comment);
        assertThat(blog.getBoards()).contains(board);

        // then
        commentService.deleteComment(blog.getName(), board.getTitle(), comment);
        verify(commentRepository).delete(comment);
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    @Transactional
    void 댓글_수정_테스트() {
        // given
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

        Comment newComment = Comment.builder()
                .content("test new comment").build();

        // when
        user.getUserComments().add(comment);
        board.setBlog(blog);

        userService.userSignUp(user);
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));

        blogService.createBlog(blog);
        when(blogRepository.findByName(blog.getName())).thenReturn(Optional.of(blog));

        boardService.createBoard(blog.getName(), board);
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));

        commentService.createComment(user.getNickname(), board.getTitle(), comment);
        comment.setId(1L);

        // then
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
        commentService.updateComment(blog.getName(), board.getTitle(), comment.getId(), newComment);
        assertThat(newComment.getContent()).isEqualTo(comment.getContent());
    }

    @Test
    @DisplayName("댓글 조회 테스트")
    @Transactional
    void 댓글_조회_테스트() {
        // given
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

        // when
        user.getUserComments().add(comment);
        board.setBlog(blog);

        userService.userSignUp(user);
        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));

        blogService.createBlog(blog);
        when(blogRepository.findByName(blog.getName())).thenReturn(Optional.of(blog));

        boardService.createBoard(blog.getName(), board);
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));

        commentService.createComment(user.getNickname(), board.getTitle(), comment);

        assertThat(commentService.getComments(board.getTitle()).get(0)).isEqualTo(comment);
    }
}