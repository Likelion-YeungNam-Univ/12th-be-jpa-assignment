package com.example.blog.domain.post.repository;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);
}

