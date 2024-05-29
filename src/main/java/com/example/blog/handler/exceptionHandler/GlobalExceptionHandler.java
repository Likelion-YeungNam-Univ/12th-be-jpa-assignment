package com.example.blog.handler.exceptionHandler;

import com.example.blog.handler.exceptionHandler.error.ErrorCode;
import com.example.blog.handler.exceptionHandler.error.ErrorDto;
import com.example.blog.handler.exceptionHandler.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.blog.handler.exceptionHandler.error.ErrorCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* CustomException 처리 */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity customException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorDto error = new ErrorDto(errorCode.getStatus(), errorCode.getMessage());
        return new ResponseEntity(error, HttpStatus.valueOf(error.getStatus()));
    }

    /* 일반 예외 처리 */
    @ExceptionHandler
    protected ResponseEntity customServerException(Exception ex) {
        ErrorDto error = new ErrorDto(INTERNAL_SERVER_ERROR.getStatus(), INTERNAL_SERVER_ERROR.getMessage());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
