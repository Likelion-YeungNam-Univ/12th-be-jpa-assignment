package com.example.blog.domain.good.repository;

import com.example.blog.domain.good.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {
}