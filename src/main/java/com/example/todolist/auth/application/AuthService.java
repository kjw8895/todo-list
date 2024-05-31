package com.example.todolist.auth.application;

import com.example.todolist.auth.application.dto.TokenDto;
import com.example.todolist.user.application.dto.UserDto;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public interface AuthService {
    TokenDto login(UserDto.Request dto);
}
