package com.example.todolist.task;

import com.example.todolist.task.application.TaskService;
import com.example.todolist.task.application.dto.TaskDto;
import com.example.todolist.task.model.Task;
import com.example.todolist.task.persistence.TaskRepository;
import com.example.todolist.user.application.UserService;
import com.example.todolist.user.application.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskRepository repository;

    @Autowired
    private UserService userService;

    private final TaskDto.Create request = new TaskDto.Create("todolist");
    private Long taskId;

    @BeforeEach
    public void setUp() {
        userService.create(new UserDto.Request("nickName", "password"));
        TaskDto.Response response = service.create(request);
        taskId = response.id();
    }

    @Test
    @WithMockUser(username= "nickName", password = "password")
    public void create() throws Exception {
        // then
        Task task = repository.findById(taskId).get();
        Assertions.assertEquals(task.getContents(), request.contents());
    }

    @Test
    @WithMockUser(username= "nickName", password = "password")
    public void fetchRecent() throws Exception {
        // when
        TaskDto.Response response = service.fetchRecent();
        // then
        Assertions.assertEquals(taskId, response.id());
    }

    @Test
    @WithMockUser(username= "nickName", password = "password")
    public void fetchAll() throws Exception {
        // when
        List<TaskDto.Response> responses = service.fetchAll();
        // then
        Assertions.assertFalse(responses.isEmpty());
    }
}
