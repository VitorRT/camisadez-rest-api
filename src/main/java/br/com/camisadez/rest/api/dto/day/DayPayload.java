/**
 * @(#)TaskPayload.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.day;

import br.com.camisadez.rest.api.dto.template.TemplateRelationShip;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * Classe <code>DayPayload</code>.
 *
 * Essa classe representa um payload DTO de um dia usado para cadastros e atualizações.
 */
@Data
public class DayPayload {
    @NotBlank(message = "o nome do dia é obrigatório.")
    private String dayName;
    private String dayDesc;

    @NotNull(message = "a data do dia é obrigatória.")
    @FutureOrPresent
    private LocalDate dateOfTheDay;

    @NotNull(message = "o template pai é obrigatório.")
    private TemplateRelationShip templateParent;
    /**
     * Construtor da classe <code>TaskPayload</code>.
     * */
    public DayPayload() {

    }

}
