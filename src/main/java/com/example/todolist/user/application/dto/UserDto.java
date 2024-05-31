package com.example.todolist.user.application.dto;

import com.example.todolist.user.model.UserStatus;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public record UserDto() {

    public record Request(String nickName, String password) {
    }

    public record Response(Long id, String nickName, UserStatus status) {
    }
}
