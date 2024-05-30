package com.example.blog.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordReq {
    private String newPassword;
}
