/**
 * @(#)TemplateSimpleResponse.java
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
public class TemplateSimpleResponse {
    private UUID id;
    private String name;
    private String includedAt;
    private String status;

    /**
     * Construtor da classe <code>TemplateSimpleResponse</code>.
     * */
    public TemplateSimpleResponse() {

    }

    /**
     * Construtor da classe <code>TemplateSimpleResponse</code>.
     *
     * @param entity
     * */
    public TemplateSimpleResponse(TemplateEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.includedAt = DateUtils.formatDateAndTime(entity.getDtIncludedAt(), entity.getTmIncludedAt());
        this.status = entity.getStatus();
    }
}
