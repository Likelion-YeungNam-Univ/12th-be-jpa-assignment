package com.example.blog.domain.like.service;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.blog.repository.BlogRepository;
import com.example.blog.domain.blog.service.BlogService;
import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.board.repository.BoardRepository;
import com.example.blog.domain.board.service.BoardService;
import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.comment.service.CommentService;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import com.example.blog.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class LikeServiceTest {

    //    @Autowired
//    private LikeService likeService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private CommentRepository boardRepository;
//
//    @Autowired
//    private CommentRepository blogRepository;
//
//    @Autowired
//    private LikeRepository likeRepository;
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
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private LikeService likeService;

    private User user;
    private Comment comment;

//    @BeforeEach
//    void setUp() {
//        User user = User.builder()
//                .email("test@test1.com")
//                .password("test")
//                .nickname("test1")
//                .birthdate(LocalDate.parse("2024-05-27"))
//                .build();
//
//        Board board = Board.builder()
//                .title("Test Title")
//                .content("Test Content")
//                .build();
//
//        Blog blog = Blog.builder()
//                .user(user)
//                .name(user.getNickname())
//                .build();
//
//        Comment comment = Comment.builder()
//                .content("test comment")
//                .build();
//
//        comment.setId(1L);
//
//        userService.userSignUp(user);
//        blogService.createBlog(blog);
//        boardService.createBoard(blog.getName(), board);
//        commentService.createComment(blog.getName(), board.getTitle(), comment);
//
//    }

    @Test
    @DisplayName("댓글 좋아요 추가 테스트")
    void 좋아요_추가_테스트() {
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

        comment.setId(1L);

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
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        likeService.clickedLike(user.getNickname(), comment.getId());

        // then
        assertThat(user.getLikes().size()).isEqualTo(1);
        assertThat(comment.getLikes().size()).isEqualTo(1);
    }

//    @Test
//    @Transactional
//    @Rollback
//    @DisplayName("사용자가 댓글에 추가한 좋아요를 제거하는 테스트")
//    void removeLikeFromComment() {
//        // given
//        likeService.clickedLike(user.getNickname(), comment.getId());
//
//        // when
//        likeService.removeLike(user.getNickname(), comment.getId());
//
//        // then
//        Optional<Like> likeOptional = likeRepository.findByUserNicknameAndCommentId(user.getNickname(), comment.getId());
//        assertThat(likeOptional).isNotPresent();
//
//        assertThat(user.getLikes()).doesNotContain(likeOptional.orElse(null));
//        assertThat(comment.getLikes()).doesNotContain(likeOptional.orElse(null));
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    @DisplayName("존재하지 않는 사용자가 좋아요를 추가하려는 경우 예외를 발생시키는 테스트")
//    void addLikeNonExistentUser() {
//        assertThatThrownBy(() -> likeService.clickedLike("nonexistentuser", comment.getId()))
//                .isInstanceOf(IllegalStateException.class)
//                .hasMessage("User does not exist.");
//    }
//
//    @Test
//    @Transactional
//    @Rollback
//    @DisplayName("존재하지 않는 댓글에 좋아요를 추가하려는 경우 예외를 발생시키는 테스트")
//    void addLikeNonExistentComment() {
//        assertThatThrownBy(() -> likeService.clickedLike(user.getNickname(), 999L))
//                .isInstanceOf(IllegalStateException.class)
//                .hasMessage("Comment does not exist.");
//    }
}
