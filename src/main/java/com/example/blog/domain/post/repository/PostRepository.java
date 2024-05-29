package com.example.blog.domain.post.repository;

import com.example.blog.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserUsername(String username);
    List <Post> findByTitle(String title);

    List<Post> findByTitleOrContent(String title, String content);

}
