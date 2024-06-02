/**
 * @(#)TaskSimpleResponse.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.day;

import br.com.camisadez.rest.api.entity.DayEntity;
import br.com.camisadez.rest.api.utils.DateUtils;
import lombok.Data;

import java.util.UUID;

/**
 * Classe <code>DaySimpleResponse</code>.
 *
 * Essa classe representa um payload DTO de um dia usado para listagens
 */
@Data
public class DaySimpleResponse {
    private UUID id;
    private String dayName;
    private String includedAt;
    private Double progress;

    /**
     * Construtor da classe <code>DaySimpleResponse</code>.
     * */
    public DaySimpleResponse() { }

    /**
     * Construtor da classe <code>DaySimpleResponse</code>.
     *
     * @param entity
     * */
    public DaySimpleResponse(DayEntity entity) {
        this.id = entity.getId();
        this.dayName = entity.getDayName();
        this.includedAt = DateUtils.formatDateAndTime(entity.getDtIncludedAt(), entity.getTmIncludedAt());
        this.progress = 50.0;
    }
}
