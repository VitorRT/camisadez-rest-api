/**
 * @(#)TaskService.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.service;

import br.com.camisadez.rest.api.dto.task.TaskFullResponse;
import br.com.camisadez.rest.api.dto.task.TaskPayload;
import br.com.camisadez.rest.api.dto.task.TaskSimpleResponse;
import br.com.camisadez.rest.api.dto.task.TaskSimpleResponseForListing;
import br.com.camisadez.rest.api.entity.TaskEntity;
import br.com.camisadez.rest.api.entity.TemplateEntity;
import br.com.camisadez.rest.api.exception.IncorrectlyFormattedDateException;
import br.com.camisadez.rest.api.repository.TaskRepository;
import br.com.camisadez.rest.api.repository.TemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.sql.Template;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe <code>TaskService</code>
 *
 * Essa classe concentra todos os serviços relacionados ao recurso de task.
 * */
@Service
public class TaskService {
    /**
     * Dependências.
     * */
    private TaskRepository taskRepository;
    private TemplateRepository templateRepository;

    /**
     * Construtor da classe <code>TaskService</code> para injeção de dependência.
     * */
    public TaskService(TaskRepository taskRepository, TemplateRepository templateRepository) {
        this.taskRepository = taskRepository;
        this.templateRepository = templateRepository;
    }

    /**
     * Serviços.
     * */

    /**
     * Esse método irá criar uma nova tarefa no sistema.
     * @param payload
     * @return persisted
     * */
    public TaskFullResponse doCreateTask(TaskPayload payload) {
        TemplateEntity templateEntity = getTemplateById(UUID.fromString(payload.getTemplateParent().getId()));

        if(!payload.isTaskWithinDateOfTheDay())
            throw new IncorrectlyFormattedDateException("As datas de prazo estão inválidas.");

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.prepareToCreate();


        taskEntity.setTaskName(payload.getTaskName());
        taskEntity.setTaskDesc(payload.getTaskDesc());
        taskEntity.setStartTask(payload.getStartTask());
        taskEntity.setEndTask(payload.getEndTask());
        taskEntity.setDateOfTheDay(payload.getDateOfTheDay());
        taskEntity.setTemplateParent(templateEntity);

        TaskEntity persisted = taskRepository.saveAndFlush(taskEntity);

        return new TaskFullResponse(persisted, templateEntity);
    }

    /**
     * Esse método irá atualizar uma nova tarefa existente no sistema.
     * @param taskId
     * @param payload
     * @return
     * */
    public TaskFullResponse doUpdateTask(UUID taskId, TaskPayload payload) {
        TemplateEntity templateEntity = getTemplateById(UUID.fromString(payload.getTemplateParent().getId()));

        if(!payload.isTaskWithinDateOfTheDay())
            throw new IncorrectlyFormattedDateException("As datas de prazo estão inválidas.");
        
        TaskEntity taskEntity = getTaskById(taskId);
        taskEntity.prepareToUpdate();

        taskEntity.setTaskName(payload.getTaskName());
        taskEntity.setTaskDesc(payload.getTaskDesc());
        taskEntity.setStartTask(payload.getStartTask());
        taskEntity.setEndTask(payload.getEndTask());
        taskEntity.setDateOfTheDay(payload.getDateOfTheDay());

        TaskEntity persisted = taskRepository.saveAndFlush(taskEntity);

        return new TaskFullResponse(persisted, templateEntity);
    }

    /**
     * Esse método irá retornar todos as tasks existentes de um usuário no sistema.
     * @return tasks
     * */
    public TaskSimpleResponseForListing getAllMyTasks(UUID templateId) {
        List<TaskSimpleResponse> tasks = taskRepository.getAllMyTemplateTasks(templateId).stream().map(TaskSimpleResponse::new).toList();
        TemplateEntity templateEntity = getTemplateById(templateId);
        TaskSimpleResponseForListing data = new TaskSimpleResponseForListing(templateEntity);
        data.setTasks(tasks);
        return data;
    }

    /**
     * Esse método irá retornar uma task específica existente de um usuário no sistema.
     * @param taskId
     * @return taskEntity
     * */
    public TaskFullResponse getMyTasks(UUID taskId) {
        TaskEntity taskEntity = getTaskById(taskId);
        TemplateEntity templateEntity = getTemplateByTaskId(taskId);
        return new TaskFullResponse(taskEntity, templateEntity);
    }

    /**
     * Esse método irá deletar uma task específica existente de um usuário no sistema.
     * @param taskId
     * */
    public void doDeleteMyTask(UUID taskId) {
        TaskEntity taskEntity = getTaskById(taskId);
        taskRepository.delete(taskEntity);
    }


    /**
     * Utils methods.
     * */

    /**
     * Retorna uma task pelo 'id'.
     * Esse método lança exceções de <code>{@link jakarta.persistence.EntityNotFoundException}</code>.
     *
     * @param id
     * @return task
     * */
    private TaskEntity getTaskById(UUID id) {
        Optional<TaskEntity> op =  taskRepository.findById(id);
        if(op.isEmpty())
            throw new EntityNotFoundException("Nenhuma tarefa foi encontrado com esse id.");
        return op.get();
    }

    private TemplateEntity getTemplateById(UUID id) {
        Optional<TemplateEntity> op =  templateRepository.findById(id);
        if(op.isEmpty())
            throw new EntityNotFoundException("Nenhum template foi encontrado com esse id.");
        return op.get();
    }

    private TemplateEntity getTemplateByTaskId(UUID id) {
        Optional<TemplateEntity> op =  templateRepository.getTemplateByTaskId(id);
        if(op.isEmpty())
            throw new EntityNotFoundException("Esse template não possui nenhuma tarefa cadastrada ou ele não existe.");
        return op.get();
    }
}
