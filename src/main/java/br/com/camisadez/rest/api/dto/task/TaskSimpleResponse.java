/**
 * @(#)TaskSimpleResponse.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.task;

import br.com.camisadez.rest.api.dto.template.TemplateRelationShipResponse;
import br.com.camisadez.rest.api.entity.TaskEntity;
import br.com.camisadez.rest.api.entity.TemplateEntity;
import br.com.camisadez.rest.api.utils.DateUtils;
import lombok.Data;

import java.util.UUID;

/**
 * Classe <code>TaskSimpleResponse</code>.
 *
 * Essa classe representa um payload DTO de uma task usado para listagens
 */
@Data
public class TaskSimpleResponse {
    private UUID id;
    private String taskName;
    private String includedAt;
    private Double progress;

    /**
     * Construtor da classe <code>TaskSimpleResponse</code>.
     * */
    public TaskSimpleResponse() { }

    /**
     * Construtor da classe <code>TaskSimpleResponse</code>.
     *
     * @param entity
     * */
    public TaskSimpleResponse(TaskEntity entity) {
        this.id = entity.getId();
        this.taskName = entity.getTaskName();
        this.includedAt = DateUtils.formatDateAndTime(entity.getDtIncludedAt(), entity.getTmIncludedAt());
        this.progress = 50.0;
    }
}
