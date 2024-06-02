/**
 * @(#)TodoTaskFullResponse.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */


package br.com.camisadez.rest.api.dto.todotask;

import br.com.camisadez.rest.api.dto.day.DayRelationShipResponse;
import br.com.camisadez.rest.api.entity.DayEntity;
import br.com.camisadez.rest.api.entity.TodoTaskEntity;
import br.com.camisadez.rest.api.utils.DateUtils;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

/**
 * Classe <code>TodoTaskFullResponse</code>.
 *
 * Essa classe representa um payload DTO de uma sub task usado para listagens
 */
@Data
public class TodoTaskFullResponse {
    private UUID id;
    private String todoName;
    private LocalTime start;
    private LocalTime end;
    private boolean hasBeenCompleted;
    private DayRelationShipResponse taskParent;
    private String createdAt;
    private String includedAt;
    private String todoColor;
    private boolean enabledToComplete;

    /**
     * Construtor da classe <code>TodoTaskFullResponse</code>.
     * */
    public TodoTaskFullResponse() {
        super();
    }

    /**
     * Construtor da classe <code>TodoTaskFullResponse</code>.
     *
     * @param entity
     * */
    public TodoTaskFullResponse(TodoTaskEntity entity, DayEntity taskParentEntity) {
        this.id = entity.getId();
        this.todoName = entity.getTodoName();
        this.start = entity.getStart();
        this.end = entity.getEnd();
        this.hasBeenCompleted = entity.getHasBeenCompleted();
        this.taskParent = new DayRelationShipResponse(taskParentEntity.getId(), taskParentEntity.getDayName());
        this.createdAt = DateUtils.formatDateAndTime(entity.getDtCreatedAt(), entity.getTmCreatedAt());
        this.includedAt = DateUtils.formatDateAndTime(entity.getDtIncludedAt(), entity.getTmIncludedAt());
        this.todoColor = entity.getTodoColor();
    }
}
