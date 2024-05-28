package com.example.blog.domain.blog.repository;

import com.example.blog.domain.blog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findByName(String name);
}
