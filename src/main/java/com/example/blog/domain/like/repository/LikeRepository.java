package com.example.blog.domain.like.repository;

import com.example.blog.domain.comment.domain.Comment;
import com.example.blog.domain.like.domain.CommentLike;
import com.example.blog.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
    List<CommentLike> findByComment(Comment comment);

}
