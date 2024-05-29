package com.example.blog.domain.like.repository;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostIdAndCommentIdAndUserId(Long postId, Long commentId, Long userId);
}
