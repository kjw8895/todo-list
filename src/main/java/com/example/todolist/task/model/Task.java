package com.example.todolist.task.model;

import com.example.todolist.common.model.BaseEntity;
import com.example.todolist.task.application.dto.TaskDto;
import com.example.todolist.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Entity
@Table(name = "TEST_TASK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONTENTS")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 50)
    private TaskStatus status = TaskStatus.PENDING;

    public void updateStatus(TaskStatus status) {
        this.status = this.status.validation(status);
    }

    public static Task create(TaskDto.Create dto, User user) {
        Task task = new Task();
        task.contents = dto.contents();
        task.user = user;
        return task;
    }
}
