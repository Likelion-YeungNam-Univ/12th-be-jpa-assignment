package com.example.blog.domain.comment.repository;

import com.example.blog.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost_Id(Long postId);

    /* like count 테스트 중 프록시 객체 참조를 해결하기 위해서 임시 사용. */
    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.likes WHERE c.id = :commentId")
    Optional<Comment> findByIdWithLikes(@Param("commentId") Long commentId);
}