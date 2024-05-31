package com.example.todolist.user.application.mapper;

import com.example.todolist.user.application.dto.UserDto;
import com.example.todolist.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Mapper
public abstract class UserDtoMapper {
    public static UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    public abstract UserDto.Response toDto(User user);

}
