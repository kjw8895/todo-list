package com.example.todolist.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private CommonExceptionCode code;
}
