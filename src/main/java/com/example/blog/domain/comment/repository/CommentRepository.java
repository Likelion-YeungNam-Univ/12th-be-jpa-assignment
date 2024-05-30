package com.example.blog.domain.comment.repository;

import com.example.blog.domain.comment.doamin.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
