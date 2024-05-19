package com.example.blog.domain.user.domain;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "post_id", nullable = false)
    private int postId;

    public Category() {
    }

    // 생성자
    public Category(String name, int postId) {
        this.name = name;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
