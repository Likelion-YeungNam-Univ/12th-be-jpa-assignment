package com.example.blog.domain.post.repository;

import com.example.blog.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserUsername(String username);
    List <Post> findByTitle(String title);

    List<Post> findByTitleOrContent(String title, String content);

}
