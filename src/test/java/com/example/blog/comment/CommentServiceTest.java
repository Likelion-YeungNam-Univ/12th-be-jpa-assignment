package com.example.blog.comment;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentReq;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.comment.service.CommentService;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    private User user;
    private Post post;

    @BeforeEach
    public void setUp() {
        commentRepository.deleteAll();

        user = User.builder()
                .username("user")
                .email("user@gmail.com")
                .password("password")
                .build();

        post = Post.builder()
                .title("Title")
                .content("Content")
                .build();

        user = userRepository.save(user);
        post = postRepository.save(post);
    }

    @Test
    @DisplayName("댓글 작성하기 테스트")
    public void createCommentTest() {
        // given
        CommentReq commentReq = CommentReq.builder()
                .content("댓글")
                .build();

        // when
        CommentRes createdComment = commentService.save(user.getId(), post.getId(), commentReq);

        // then
        Assertions.assertNotNull(createdComment.getId());
        Assertions.assertEquals("댓글", createdComment.getContent());
    }

    @Test
    @DisplayName("게시글 전체 댓글 불러오기 테스트")
    public void getAllCommentTest() {
        // given
        Comment comment1 = Comment.builder()
                .content("댓글1")
                .build();

        Comment comment2 = Comment.builder()
                .content("댓글2")
                .build();

        comment1.setUser(user);
        comment1.setPost(post);

        comment2.setUser(user);
        comment2.setPost(post);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        List<CommentRes> comments = commentService.findAll(post.getId());

        // then
        Assertions.assertEquals(2, comments.size());
        Assertions.assertEquals("댓글1", comments.get(0).getContent());
        Assertions.assertEquals("댓글2", comments.get(1).getContent());
        Assertions.assertEquals(post.getTitle(), comments.get(1).getPostTitle());
    }

    @Test
    @DisplayName("댓글 수정하기 테스트")
    public void updateCommentTest() {
        // given
        Comment comment = Comment.builder()
                .content("댓글")
                .build();

        comment.setUser(user);
        comment.setPost(post);

        comment = commentRepository.save(comment);

        CommentReq updateCommentDto = CommentReq.builder()
                .content("댓글 수정")
                .build();

        // when
        CommentRes updatedComment = commentService.update(comment.getId(), updateCommentDto);

        // then
        Assertions.assertEquals("댓글 수정", updatedComment.getContent());
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    public void deleteCommentTest() {
        // given
        Comment comment = Comment.builder()
                .content("댓글")
                .build();

        comment.setUser(user);
        comment.setPost(post);

        comment = commentRepository.save(comment);

        // when
        commentService.delete(comment.getId());

        // then
        Comment deletedComment = commentRepository.findById(comment.getId()).orElse(null);
        Assertions.assertNull(deletedComment);
    }
}
