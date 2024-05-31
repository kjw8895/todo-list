package com.example.todolist.user.application;

import com.example.todolist.user.application.dto.UserDto;
import com.example.todolist.user.model.User;

import java.util.Optional;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public interface UserService {
    Optional<User> findByNickName(String nickName);
    UserDto.Response create(UserDto.Request dto);
    boolean delete(Long id);
}
