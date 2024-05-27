package com.example.blog.domain.blog.domain;

import com.example.blog.domain.board.domain.Board;
import com.example.blog.domain.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "name", length = 30)
    @NotNull
    private String name;

    @Column(name = "blogurl", length = 60)
    @NotNull
    private String blogUrl;

    @OneToMany(mappedBy = "blog")
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Blog(String name, String blogUrl) {
        this.name = name;
        this.blogUrl = blogUrl;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
