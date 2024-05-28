package com.example.blog.domain.board.repository;

import com.example.blog.domain.blog.domain.Blog;
import com.example.blog.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByTitle(String title);

    Optional<Board> findByContent(String title);

    Optional<Board> findByBlog(Blog blog);

    Optional<Board> deleteByTitle(String name);

}