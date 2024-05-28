package com.example.blog.post;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.dto.PostReq;
import com.example.blog.domain.post.dto.PostRes;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.post.service.PostService;
import com.example.blog.domain.user.domain.User;
import com.example.blog.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;


    @BeforeAll
    public static void beforeAll() {

    }
    @BeforeEach
    public void beforeEach() {
        postRepository.deleteAll();
        userRepository.deleteAll();

        user = User.builder()
                .username("user")
                .email("user@gmail.com")
                .password("password")
                .build();
    }

    @Test
    @DisplayName("게시글 작성 테스트")
    public void createPost() {
        // given
        user = userRepository.save(user);

        PostReq postReq = PostReq.builder()
                .title("Title")
                .content("Content")
                .build();

        // when
        PostRes createdPost = postService.createPost(user.getId(), postReq);

        // then
        Assertions.assertNotNull(createdPost.getId());
        Assertions.assertEquals("Title", createdPost.getTitle());
        Assertions.assertEquals("Content", createdPost.getContent());
    }

    @Test
    @DisplayName("게시글 조회 테스트")
    public void getPostTest() {
        // given
        Post post = Post.builder()
                .title("Title")
                .content("Content")
                .build();
        userRepository.save(user);
        post.setUser(user);
        postRepository.save(post);

        // when
        PostRes foundPost = postService.getPost(post.getId());

        // then
        Assertions.assertNotNull(foundPost);
        Assertions.assertEquals(post.getId(), foundPost.getId());
        Assertions.assertEquals(post.getTitle(), foundPost.getTitle());
        Assertions.assertEquals(post.getContent(), foundPost.getContent());
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    public void deletePostTest() {
        // given
        Post post = Post.builder()
                .title("Title")
                .content("Content")
                .build();

        postRepository.save(post);

        // when
        postService.deletePost(post.getId());

        // then
        Post deletedPost = postRepository.findById(post.getId()).orElse(null);
        Assertions.assertNull(deletedPost);
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    public void updatePostTest() {
        // given
        Post post = Post.builder()
                .title("Title")
                .content("Content")
                .build();

        postRepository.save(post);

        PostReq postReq = PostReq.builder()
                .title("Updated Title")
                .content("Updated Content")
                .build();

        // when
        PostRes updatedPost = postService.updatePost(post.getId(), postReq);

        // then
        Assertions.assertEquals("Updated Title", updatedPost.getTitle());
        Assertions.assertEquals("Updated Content", updatedPost.getContent());
    }

    @Test
    @DisplayName("게시글 조회수 증가 테스트")
    public void updateViewTest() {
        // given
        Post post = Post.builder()
                .title("Title")
                .content("Content")
                .build();

        postRepository.save(post);

        // when
        postService.updateView(post.getId());

        // then
        Assertions.assertEquals(1, post.getView());
    }

    @Test
    @DisplayName("전체 게시글 조회 테스트")
    public void getAllPostTest() {
        // given
        Post post1 = Post.builder()
                .title("Title1")
                .content("Content1")
                .build();

        Post post2 = Post.builder()
                .title("Title2")
                .content("Content2")
                .build();

        postRepository.save(post1);
        postRepository.save(post2);

        // when
        List<PostRes> posts = postService.getAllPost();

        // then
        Assertions.assertEquals(2, posts.size());
        Assertions.assertEquals("Title1", posts.get(0).getTitle());
        Assertions.assertEquals("Content1", posts.get(0).getContent());
        Assertions.assertEquals("Title2", posts.get(1).getTitle());
        Assertions.assertEquals("Content2", posts.get(1).getContent());
    }

    @Test
    @DisplayName("제목으로 게시글 검색 테스트")
    public void readPostByTitleTest() {
        // given
        Post post1 = Post.builder()
                .title("like-Lion")
                .content("Lion haha")
                .build();

        Post post2 = Post.builder()
                .title("like-Tiger")
                .content("Tiger haha")
                .build();

        postRepository.save(post1);
        postRepository.save(post2);

        // when
        List<PostRes> posts = postService.readPostByTitle("Lion");

        // then
        Assertions.assertEquals(1, posts.size());
        Assertions.assertEquals("like-Lion", posts.get(0).getTitle());
    }

    @Test
    @DisplayName("내용으로 게시글 검색 테스트")
    public void readPostByContentTest() {
        // given
        Post post1 = Post.builder()
                .title("like-Lion")
                .content("Lion haha")
                .build();

        Post post2 = Post.builder()
                .title("like-Tiger")
                .content("Tiger haha")
                .build();

        postRepository.save(post1);
        postRepository.save(post2);

        // when
        List<PostRes> posts = postService.readPostByContent("Lion");

        // then
        Assertions.assertEquals(1, posts.size());
        Assertions.assertEquals("Lion haha", posts.get(0).getContent());
    }
}
