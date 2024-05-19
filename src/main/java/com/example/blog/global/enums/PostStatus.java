package com.example.blog.global.enums;

import lombok.Getter;

@Getter
public enum PostStatus {
    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE");

    private final String value;

    PostStatus(String value){this.value = value;}
}
