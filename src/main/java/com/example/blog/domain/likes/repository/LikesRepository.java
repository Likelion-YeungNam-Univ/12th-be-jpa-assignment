package com.example.blog.domain.likes.repository;

import com.example.blog.domain.comment.doamin.Comment;
import com.example.blog.domain.likes.domain.Likes;
import com.example.blog.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserAndComment(User user, Comment comment);
}
