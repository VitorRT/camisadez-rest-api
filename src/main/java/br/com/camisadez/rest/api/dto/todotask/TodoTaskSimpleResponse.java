/**
 * @(#)TodoTaskSimpleResponse.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.todotask;

import br.com.camisadez.rest.api.entity.TodoTaskEntity;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

/**
 * Classe <code>TodoTaskSimpleResponse</code>.
 *
 * Essa classe representa um payload DTO de uma sub task usado para listagens
 */
@Data
public class TodoTaskSimpleResponse {
    private UUID id;
    private String todoName;
    private LocalTime start;
    private LocalTime end;
    private boolean hasBeenCompleted;
    private String todoColor;

    /**
     * Construtor da classe <code>TodoTaskSimpleResponse</code>.
     * */
    public TodoTaskSimpleResponse() {
        super();
    }

    /**
     * Construtor da classe <code>TodoTaskSimpleResponse</code>.
     *
     * @param entity
     * */
    public TodoTaskSimpleResponse(TodoTaskEntity entity) {
        this.id = entity.getId();
        this.todoName = entity.getTodoName();
        this.start = entity.getStart();
        this.end = entity.getEnd();
        this.hasBeenCompleted = entity.getHasBeenCompleted();
        this.todoColor = entity.getTodoColor();
    }
}
