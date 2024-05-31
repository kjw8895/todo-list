package com.example.todolist.task.application.dto;

import com.example.todolist.task.model.TaskStatus;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public record TaskDto() {
    public record Create(String contents) {
    }
    public record Update(TaskStatus status) {
    }
    public record Response(Long id, String contents, TaskStatus status, Long createdAt) {
    }
}
