/**
 * @(#)TaskFullResponse.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.day;

import br.com.camisadez.rest.api.dto.template.TemplateRelationShipResponse;
import br.com.camisadez.rest.api.entity.DayEntity;
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
public class DayFullResponse {
    private UUID id;
    private String dayName;
    private String dayDesc;
    private String createdAt;
    private String includedAt;
    private Double progress;
    private TemplateRelationShipResponse templateParent;

    /**
     * Construtor da classe <code>TaskFullResponse</code>.
     *
     * @param entity
     * */
    public DayFullResponse(DayEntity entity, TemplateEntity templateParentEntity) {
        this.id = entity.getId();
        this.dayName = entity.getDayName();
        this.dayDesc = entity.getDayDesc();
        this.createdAt = DateUtils.formatDateAndTime(entity.getDtCreatedAt(), entity.getTmCreatedAt());
        this.includedAt = DateUtils.formatDateAndTime(entity.getDtIncludedAt(), entity.getTmIncludedAt());
        this.progress = 50.0;
        this.templateParent = new TemplateRelationShipResponse(
                templateParentEntity.getId(),
                templateParentEntity.getName()
        );
    }
}
