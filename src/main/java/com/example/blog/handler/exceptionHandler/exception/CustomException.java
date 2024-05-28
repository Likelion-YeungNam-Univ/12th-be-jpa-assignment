package com.example.blog.handler.exceptionHandler.exception;

import com.example.blog.handler.exceptionHandler.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
}