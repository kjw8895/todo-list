package com.example.todolist.user.application.impl;

import com.example.todolist.common.exception.CommonException;
import com.example.todolist.common.exception.CommonExceptionCode;
import com.example.todolist.common.utils.AuthUtils;
import com.example.todolist.role.model.Role;
import com.example.todolist.role.model.RoleType;
import com.example.todolist.role.persistence.RoleRepository;
import com.example.todolist.user.application.UserService;
import com.example.todolist.user.application.dto.UserDto;
import com.example.todolist.user.application.mapper.UserDtoMapper;
import com.example.todolist.user.model.User;
import com.example.todolist.user.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByNickName(String nickName) {
        return repository.findByNickName(nickName);
    }

    @Override
    @Transactional
    public UserDto.Response create(UserDto.Request dto) {
        Optional<User> userOptional = findByNickName(dto.nickName());
        if (userOptional.isPresent()) {
            throw new CommonException(CommonExceptionCode.USER_ALREADY_EXISTS);
        }

        User user = repository.save(User.create(dto, passwordEncoder.encode(dto.password())));
        Role defaultRole = roleRepository.findByType(RoleType.ROLE_USER)
                .orElseThrow(() -> new CommonException(CommonExceptionCode.NOT_FOUND_RESOURCE));

        user.addRole(defaultRole);
        user = repository.save(user);

        return UserDtoMapper.INSTANCE.toDto(user);
    }

    @Override
    public boolean delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new CommonException(CommonExceptionCode.NOT_FOUND_RESOURCE));
        // 본인의 계정만 탈퇴 가능
        if (!AuthUtils.matches(user.getNickName())) {
            throw new CommonException(CommonExceptionCode.INVALID_REQUEST);
        }
        user.withdrawal();
        repository.save(user);
        return true;
    }
}
