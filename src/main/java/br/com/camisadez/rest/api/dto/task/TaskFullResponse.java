/**
 * @(#)TaskFullResponse.java
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
 * Classe <code>TaskFullResponse</code>.
 *
 * Essa classe representa um payload DTO de uma task usado para listagens
 */
@Data
public class TaskFullResponse {
    private UUID id;
    private String taskName;
    private String taskDesc;
    private String createdAt;
    private String includedAt;
    private Double progress;
    private TemplateRelationShipResponse templateParent;

    /**
     * Construtor da classe <code>TaskFullResponse</code>.
     *
     * @param entity
     * */
    public TaskFullResponse(TaskEntity entity, TemplateEntity templateParentEntity) {
        this.id = entity.getId();
        this.taskName = entity.getTaskName();
        this.taskDesc = entity.getTaskDesc();
        this.createdAt = DateUtils.formatDateAndTime(entity.getDtCreatedAt(), entity.getTmCreatedAt());
        this.includedAt = DateUtils.formatDateAndTime(entity.getDtIncludedAt(), entity.getTmIncludedAt());
        this.progress = 50.0;
        this.templateParent = new TemplateRelationShipResponse(
                templateParentEntity.getId(),
                templateParentEntity.getName()
        );
    }
}
