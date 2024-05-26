/**
 * @(#)TaskController.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.controller;

import br.com.camisadez.rest.api.dto.response.GenericResponse;
import br.com.camisadez.rest.api.dto.task.TaskPayload;
import br.com.camisadez.rest.api.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Classe <code>{@link TaskController}</code>.
 *
 * Essa classe controladora é responsável por receber requisições HTTP para o recurso de tasks.
 * */
@RestController
@RequestMapping("/task")
public class TaskController {
    /**
     * Dependências.
     * */
    private TaskService taskService;

    /**
     * Construtor da classe <code>TaskController</code> para injeção de dependências.
     * */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> doCreateTask(@RequestBody @Validated TaskPayload payload) {
        GenericResponse response = new GenericResponse();
        response.setData(taskService.doCreateTask(payload));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<GenericResponse> doUpdateTask(@PathVariable UUID taskId, @RequestBody @Validated TaskPayload payload) {
        GenericResponse response = new GenericResponse();
        response.setData(taskService.doUpdateTask(taskId, payload));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> getAllMyTemplateTasks(@RequestParam(name="templateParentID", required=true) UUID templateId) {
        GenericResponse response = new GenericResponse();
        response.setData(taskService.getAllMyTasks(templateId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/show/{taskId}")
    public ResponseEntity<GenericResponse> getMyTask(@PathVariable UUID taskId) {
        GenericResponse response = new GenericResponse();
        response.setData(taskService.getMyTasks(taskId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> doDeleteMyTask(@PathVariable UUID taskId) {
        taskService.doDeleteMyTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
