package com.example.blog.domain.post.repository;

import com.example.blog.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByTitle(String title);

    Optional<Post> findByTitleOrContent(String title, String content);

    @Query("select p from Post p join fetch p.user left join fetch p.comments c left join fetch c.likes where p.id = :id")
    Optional<Post> findFetchById(Long id);
}
