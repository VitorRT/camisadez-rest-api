/**
 * @(#)TemplateFullResponse.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.template;

import br.com.camisadez.rest.api.entity.TemplateEntity;
import br.com.camisadez.rest.api.utils.DateUtils;
import lombok.Data;

import java.util.UUID;

/**
 * Classe <code>TemplateFullResponse</code>.
 *
 * Essa classe representa um payload DTO de um template usado para listagens
 */
@Data
public class TemplateFullResponse {
    private UUID id;
    private String name;
    private String description;
    private String createdAt;
    private String includedAt;
    private String status;
    private String createdBy;

    /**
     * Construtor da classe <code>TemplateFullResponse</code>.
     * */
    public TemplateFullResponse() {

    }

    /**
     * Construtor da classe <code>TemplateFullResponse</code>.
     *
     * @param entity
     * */
    public TemplateFullResponse(TemplateEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.createdAt = DateUtils.formatDateAndTime(entity.getDtCreatedAt(), entity.getTmCreatedAt());
        this.includedAt = DateUtils.formatDateAndTime(entity.getDtIncludedAt(), entity.getTmIncludedAt());
        this.status = entity.getStatus();
        this.createdBy = entity.getCreatedBy();
    }
}
