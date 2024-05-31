package com.example.todolist.auth;

import com.example.todolist.auth.application.AuthService;
import com.example.todolist.auth.application.dto.TokenDto;
import com.example.todolist.common.utils.JwtProvider;
import com.example.todolist.user.application.UserService;
import com.example.todolist.user.application.dto.UserDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private AuthService service;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    private UserDto.Request request;

    @BeforeEach
    public void setUp() {
        request = new UserDto.Request("nickName", "password");
        userService.create(request);
    }

    @Test
    @Transactional
    public void login() throws Exception {
        // when
        TokenDto token = service.login(request);

        // then
        Assertions.assertTrue(jwtProvider.validateToken(token.accessToken()));
    }

}
