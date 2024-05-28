package com.example.blog.domain.user.dto;

import com.example.blog.domain.user.domain.User;
import lombok.Getter;

@Getter
public class UserRes {
    private final Long id;
    private final String username;
    private final String email;

    public UserRes(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
