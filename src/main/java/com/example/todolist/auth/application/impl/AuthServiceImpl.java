package com.example.todolist.auth.application.impl;

import com.example.todolist.auth.application.AuthService;
import com.example.todolist.auth.application.dto.TokenDto;
import com.example.todolist.common.exception.CommonException;
import com.example.todolist.common.exception.CommonExceptionCode;
import com.example.todolist.common.utils.JwtProvider;
import com.example.todolist.user.application.UserService;
import com.example.todolist.user.application.dto.UserDto;
import com.example.todolist.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenDto login(UserDto.Request dto) {
        User user = userService.findByNickName(dto.nickName())
                .orElseThrow(() -> new CommonException(CommonExceptionCode.NOT_FOUND_RESOURCE));
        userValidation(user, dto);
        return new TokenDto(jwtProvider.createToken(user));
    }

    private void userValidation(User user, UserDto.Request dto) {
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new CommonException(CommonExceptionCode.PASSWORD_NOT_MATCH);
        }
    }
}
