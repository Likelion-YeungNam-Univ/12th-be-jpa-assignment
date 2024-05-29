package com.example.blog.domain.like.repository;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.like.domain.Like;
import com.example.blog.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByUserAndComment(User user, Comment comment);
    Optional<Like> findByUser_IdAndComment_Id(Long userId, Long commentId);
}
