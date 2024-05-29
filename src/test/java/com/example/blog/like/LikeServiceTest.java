package com.example.blog.like;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.comment.dto.CommentRes;
import com.example.blog.domain.comment.repository.CommentRepository;
import com.example.blog.domain.comment.service.CommentService;
import com.example.blog.domain.like.service.LikeService;
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

@SpringBootTest
public class LikeServiceTest {

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private User user;
    private Post post;
    private Comment comment;
    @BeforeEach
    public void setUp() {
        user = User.builder()
                .username("user")
                .email("user@gmail.com")
                .password("password")
                .build();

        post = Post.builder()
                .title("Title")
                .content("Content")
                .build();

        comment = Comment.builder()
                .content("Comment Content")
                .build();

        comment.setUser(user);
        comment.setPost(post);

        user = userRepository.save(user);
        post = postRepository.save(post);
        comment = commentRepository.save(comment);
    }

    /*
    * 댓글 좋아요, 좋아요 취소를 한번에 보기 위해 한 테스트에서 실행
    */
    @Test
    @DisplayName("댓글 좋아요/좋아요 취소 테스트")
    public void likeCommentTest() {
        // given

        // when
        likeService.likeComment(user.getId(), comment.getId());

        // then
        Comment foundComment1 = commentRepository.findByIdWithLikes(comment.getId()).orElse(null);
        Assertions.assertEquals(1, foundComment1.getLikes().size());

        // when
        likeService.unLikeComment(user.getId(), comment.getId());

        // then
        Comment foundComment2 = commentRepository.findByIdWithLikes(comment.getId()).orElse(null);
        Assertions.assertEquals(0, foundComment2.getLikes().size());
    }
}
