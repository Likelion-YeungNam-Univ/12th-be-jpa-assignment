package com.example.blog.domain.like.repository;

import com.example.blog.domain.like.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByPostIdAndCommentIdAndUserId(Long postId, Long commentId, Long userId);
}
