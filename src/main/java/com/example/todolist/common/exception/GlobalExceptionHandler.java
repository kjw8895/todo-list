package com.example.todolist.common.exception;

import com.example.todolist.common.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handle(CommonException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getCode()), e.getCode().getStatus());
    }
}
