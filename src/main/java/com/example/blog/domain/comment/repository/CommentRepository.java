package com.example.blog.domain.comment.repository;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
