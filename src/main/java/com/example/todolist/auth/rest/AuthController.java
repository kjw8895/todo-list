package com.example.todolist.auth.rest;

import com.example.todolist.auth.application.AuthService;
import com.example.todolist.auth.application.dto.TokenDto;
import com.example.todolist.common.response.GenericResponse;
import com.example.todolist.user.application.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @GetMapping(value = "/login")
    public ResponseEntity<GenericResponse<TokenDto>> login(UserDto.Request dto) {
        return GenericResponse.ok(service.login(dto));
    }

}
