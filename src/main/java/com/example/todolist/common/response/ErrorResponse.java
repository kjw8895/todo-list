package com.example.todolist.common.response;

import com.example.todolist.common.exception.CommonExceptionCode;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Getter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer code;
    private String msg;

    public ErrorResponse(CommonExceptionCode code) {
        this.timestamp = LocalDateTime.now();
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}
