package com.example.blog.post;

import com.example.blog.BlogApplication;
import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.post.repository.PostRepository;
import com.example.blog.domain.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = BlogApplication.class)
public class PostRepositoryTest {

    @Autowired
    private PostRepository postsRepository;

    @BeforeEach
    public void setUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        User user = User.builder()
                .username("test")
                .password("test")
                .email("email")
                .build();

        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        post.setUser(user);

        postsRepository.save(post);

        // when
        List<Post> postsList = postsRepository.findAll();

        // then
        Post posts = postsList.get(0);
        Assertions.assertEquals(title, posts.getTitle());
        Assertions.assertEquals(content, posts.getContent());
    }
}
