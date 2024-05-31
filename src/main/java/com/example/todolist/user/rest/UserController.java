package com.example.todolist.user.rest;

import com.example.todolist.common.constant.WebPrefix;
import com.example.todolist.common.response.GenericResponse;
import com.example.todolist.user.application.UserService;
import com.example.todolist.user.application.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WebPrefix.user)
public class UserController {
    private final UserService service;

    /**
     * 회원 가입
     */
    @PostMapping
    public ResponseEntity<GenericResponse<UserDto.Response>> create(@RequestBody UserDto.Request dto) {
        return GenericResponse.ok(service.create(dto));
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GenericResponse<Boolean>> delete(@PathVariable Long id) {
        return GenericResponse.ok(service.delete(id));
    }

}
