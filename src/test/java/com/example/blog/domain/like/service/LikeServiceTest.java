package com.example.blog.domain.like.service;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.blog.repository.BlogRepository;
import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.board.repository.BoardRepository;
import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.like.domain.Like;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class LikeServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BlogRepository blogRepository;

    @MockBean
    private BoardRepository boardRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Test
    @DisplayName("댓글 좋아요 추가 테스트")
    @Transactional
    void 좋아요_추가_테스트() {
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

        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        Comment comment = Comment.builder()
                .content("test comment")
                .build();

        comment.setId(1L);

        Like like = Like.builder()
                .user(user)
                .comment(comment)
                .build();

        // when
        user.getUserComments().add(comment);
        board.setBlog(blog);
        board.getBoardComments().add(comment);
        userRepository.save(user);
        blogRepository.save(blog);
        boardRepository.save(board);
        commentRepository.save(comment);

        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        board.getBoardComments().get(0).getLikes().add(like);
        user.addLike(like);

        // then
        assertThat(user.getLikes().size()).isEqualTo(1);
        assertThat(comment.getLikes().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("좋아요_취소_테스트")
    @Transactional
    void 좋아요_취소() {
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

        Board board = Board.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        Comment comment = Comment.builder()
                .content("test comment")
                .build();

        comment.setId(1L);

        Like like = Like.builder()
                .user(user)
                .comment(comment)
                .build();

        // when
        user.getUserComments().add(comment);
        board.setBlog(blog);
        board.getBoardComments().add(comment);
        userRepository.save(user);
        blogRepository.save(blog);
        boardRepository.save(board);
        commentRepository.save(comment);

        when(userRepository.findByNickname(user.getNickname())).thenReturn(Optional.of(user));
        when(boardRepository.findByTitle(board.getTitle())).thenReturn(Optional.of(board));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        board.getBoardComments().get(0).getLikes().add(like);
        user.addLike(like);

        // then
        board.getBoardComments().get(0).getLikes().remove(like);
        user.removeLike(like);

        assertThat(user.getLikes().size()).isEqualTo(0);
        assertThat(comment.getLikes().size()).isEqualTo(0);
    }
}
