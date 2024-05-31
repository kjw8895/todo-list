package com.example.todolist.task.persistence.impl;

import com.example.todolist.task.model.QTask;
import com.example.todolist.task.model.Task;
import com.example.todolist.task.persistence.CustomTaskRepository;
import com.example.todolist.user.model.QUser;
import com.example.todolist.user.model.User;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements CustomTaskRepository {
    private final JPAQueryFactory queryFactory;
    public static QTask qTask = QTask.task;
    public static QUser qUser = QUser.user;

    @Override
    public Optional<Task> fetchRecent(User user) {
        JPAQuery<Task> query = queryFactory
                .from(qTask)
                .join(qTask.user, qUser).on(qUser.eq(user))
                .orderBy(qTask.id.desc())
                .select(qTask);

        return Optional.ofNullable(query.fetchFirst());
    }
}
