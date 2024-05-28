package com.example.blog.domain.blog.repository;

import com.example.blog.domain.blog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
