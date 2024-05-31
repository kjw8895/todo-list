package com.example.todolist.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Getter
@AllArgsConstructor
public class GenericResponse<T> {
    private T data;

    public static <T> ResponseEntity<GenericResponse<T>> ok(final T data) {
        return of(HttpStatus.OK, data);
    }

    public static <T> ResponseEntity<GenericResponse<T>> of(final HttpStatus status,
                                                            final T data) {
        return ResponseEntity.status(status).body(new GenericResponse<>(data));
    }

}
