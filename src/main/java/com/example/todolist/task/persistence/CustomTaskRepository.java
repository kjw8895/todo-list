package com.example.todolist.task.persistence;

import com.example.todolist.task.model.Task;
import com.example.todolist.user.model.User;

import java.util.Optional;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public interface CustomTaskRepository {
    Optional<Task> fetchRecent(User user);
}
