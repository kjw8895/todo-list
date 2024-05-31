package com.example.todolist.task.rest;

import com.example.todolist.common.constant.WebPrefix;
import com.example.todolist.common.response.GenericResponse;
import com.example.todolist.task.application.TaskService;
import com.example.todolist.task.application.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@RestController
@RequestMapping(value = WebPrefix.user + "/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    /**
     * 가장 최근 todolist 조회
     */
    @GetMapping(value = "/recent")
    public ResponseEntity<GenericResponse<TaskDto.Response>> recent() {
        return GenericResponse.ok(service.fetchRecent());
    }

    /**
     * 모든 todolist 조회
     */
    @GetMapping(value = "/all")
    public ResponseEntity<GenericResponse<List<TaskDto.Response>>> fetchAll() {
        return GenericResponse.ok(service.fetchAll());
    }

    /**
     * todolist 생성
     */
    @PostMapping
    public ResponseEntity<GenericResponse<TaskDto.Response>> create(@RequestBody TaskDto.Create dto) {
        return GenericResponse.ok(service.create(dto));
    }

    /**
     * todolist 상태 업데이트
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<GenericResponse<TaskDto.Response>> update(@PathVariable Long id, @RequestBody TaskDto.Update dto) {
        return GenericResponse.ok(service.update(id, dto));
    }
}
