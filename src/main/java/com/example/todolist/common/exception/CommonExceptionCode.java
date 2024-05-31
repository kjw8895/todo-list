package com.example.todolist.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Getter
public enum CommonExceptionCode {
    USER_ALREADY_EXISTS(1000, HttpStatus.BAD_REQUEST, "User already exists"),
    NOT_FOUND_RESOURCE(1001, HttpStatus.NOT_FOUND, "Not found resource"),
    PASSWORD_NOT_MATCH(1002, HttpStatus.BAD_REQUEST, "Password not match"),
    INVALID_REQUEST(1003, HttpStatus.BAD_REQUEST, "Invalid request");
    private final int code;
    private final HttpStatus status;
    private final String msg;

    CommonExceptionCode(int code, HttpStatus status, String msg) {
        this.code = code;
        this.status = status;
        this.msg = msg;
    }
}
