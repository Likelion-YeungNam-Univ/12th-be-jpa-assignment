package com.example.blog.domain.user.dto;

import com.example.blog.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserReq {

    private String username;
    private String email;
    private String password;

    public User toUserEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
