package com.example.blog.domain.post.repository;

import com.example.blog.domain.post.domain.Post;
import com.example.blog.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

