package com.example.todolist.task.application.mapper;

import com.example.todolist.common.mapper.BaseMapper;
import com.example.todolist.task.application.dto.TaskDto;
import com.example.todolist.task.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Mapper
public abstract class TaskDtoMapper extends BaseMapper {
    public static TaskDtoMapper INSTANCE = Mappers.getMapper(TaskDtoMapper.class);

    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "convertDateToTime")
    public abstract TaskDto.Response toDto(Task task);

}
