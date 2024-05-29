package com.example.blog.domain.post.repository;

import com.example.blog.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByTitle(String title);

    Optional<Post> findByTitleOrContent(String title, String content);

    //조회수 증가
    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
    int updateView(@Param("id") Long id);
}
