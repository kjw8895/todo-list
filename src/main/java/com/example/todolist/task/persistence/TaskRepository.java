package com.example.todolist.task.persistence;

import com.example.todolist.task.model.Task;
import com.example.todolist.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public interface TaskRepository extends JpaRepository<Task, Long>, CustomTaskRepository {
    List<Task> findAllByUser(User user);
    Optional<Task> findByUserAndId(User user, long id);
}
