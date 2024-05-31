package com.example.todolist.task.application;

import com.example.todolist.task.application.dto.TaskDto;

import java.util.List;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public interface TaskService {
    TaskDto.Response fetchRecent();
    List<TaskDto.Response> fetchAll();
    TaskDto.Response create(TaskDto.Create dto);
    TaskDto.Response update(Long id, TaskDto.Update dto);
}
