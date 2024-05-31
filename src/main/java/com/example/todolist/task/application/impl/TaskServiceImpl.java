package com.example.todolist.task.application.impl;

import com.example.todolist.common.exception.CommonException;
import com.example.todolist.common.exception.CommonExceptionCode;
import com.example.todolist.common.utils.AuthUtils;
import com.example.todolist.task.application.TaskService;
import com.example.todolist.task.application.dto.TaskDto;
import com.example.todolist.task.application.mapper.TaskDtoMapper;
import com.example.todolist.task.model.Task;
import com.example.todolist.task.persistence.TaskRepository;
import com.example.todolist.user.application.UserService;
import com.example.todolist.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final UserService userService;

    @Override
    public TaskDto.Response fetchRecent() {
        User user = findLoginUser();
        Optional<Task> taskOptional = repository.fetchRecent(user);
        return taskOptional.map(task -> TaskDtoMapper.INSTANCE.toDto(task)).orElse(null);
    }

    @Override
    public List<TaskDto.Response> fetchAll() {
        User user = findLoginUser();
        return repository.findAllByUser(user).stream().map(TaskDtoMapper.INSTANCE::toDto).toList();
    }

    @Override
    @Transactional
    public TaskDto.Response create(TaskDto.Create dto) {
        User user = findLoginUser();
        Task task = repository.save(Task.create(dto, user));
        return TaskDtoMapper.INSTANCE.toDto(task);
    }

    @Override
    public TaskDto.Response update(Long id, TaskDto.Update dto) {
        User user = findLoginUser();
        Task task = repository.findByUserAndId(user, id).orElseThrow(() -> new CommonException(CommonExceptionCode.NOT_FOUND_RESOURCE));
        task.updateStatus(dto.status());
        return TaskDtoMapper.INSTANCE.toDto(repository.save(task));
    }

    private User findLoginUser() {
        return userService.findByNickName(AuthUtils.getAuth().getName())
                .orElseThrow(() -> new CommonException(CommonExceptionCode.INVALID_REQUEST));
    }
}
