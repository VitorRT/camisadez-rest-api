package br.com.camisadez.rest.api.controller;

import br.com.camisadez.rest.api.dto.response.GenericResponse;
import br.com.camisadez.rest.api.dto.todotask.TodoTaskPayload;
import br.com.camisadez.rest.api.dto.todotask.TodoTaskPayloadByList;
import br.com.camisadez.rest.api.service.TodoTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/day/todo")
public class TodoTaskController {
    private TodoTaskService todoService;

    public TodoTaskController(TodoTaskService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> doCreateTodoTask(@RequestBody @Validated TodoTaskPayload payload) {
        GenericResponse response = new GenericResponse();
        response.setData(todoService.doCreateTodoTask(payload, false));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/create-by-list")
    public ResponseEntity<GenericResponse> doCreateTodoTaskByList(@RequestBody @Validated TodoTaskPayloadByList payload) {
        GenericResponse response = new GenericResponse();
        response.setData(todoService.doCreateTodoTaskByList(payload));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{todoId}")
    public ResponseEntity<GenericResponse> doUpdateTodoTask(@PathVariable String todoId, @RequestBody @Validated TodoTaskPayload payload) {
        GenericResponse response = new GenericResponse();
        response.setData(todoService.doUpdateTodoTask(UUID.fromString(todoId), payload));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/show/{todoId}")
    public ResponseEntity<GenericResponse> getMyTodoTask(@PathVariable String todoId) {
        GenericResponse response = new GenericResponse();
        response.setData(todoService.getMyTodoTask(UUID.fromString(todoId)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list/{taskId}")
    public ResponseEntity<GenericResponse> getAllMyTodoTasks(@PathVariable String taskId) {
        GenericResponse response = new GenericResponse();
        response.setData(todoService.getAllMyTodoTasks(UUID.fromString(taskId)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity<Void> doDeleteMyTask(@PathVariable String todoId) {
        todoService.doDeleteMyTask(UUID.fromString(todoId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/complete/{todoId}")
    public ResponseEntity<GenericResponse> doCompleteTodo(@PathVariable String todoId) {
        GenericResponse response = new GenericResponse();
        response.setData(todoService.doCompleteTodo(UUID.fromString(todoId)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/uncomplete/{todoId}")
    public ResponseEntity<GenericResponse> doUncompleteTodo(@PathVariable String todoId) {
        GenericResponse response = new GenericResponse();
        response.setData(todoService.doUncompleteTodo(UUID.fromString(todoId)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
