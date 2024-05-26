/**
 * @(#)TaskPayload.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.task;

import br.com.camisadez.rest.api.dto.template.TemplateRelationShip;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe <code>TaskPayload</code>.
 *
 * Essa classe representa um payload DTO de uma task usado para cadastros e atualizações.
 */
@Data
public class TaskPayload {
    @NotBlank(message = "o nome da tarefa é obrigatório.")
    private String taskName;
    private String taskDesc;

    @NotNull(message = "a data de início é obrigatória.")
    private LocalDateTime startTask;

    @NotNull(message = "a data de término é obrigatória.")
    private LocalDateTime endTask;

    @NotNull(message = "a data do dia é obrigatória.")
    private LocalDate dateOfTheDay;

    @NotNull(message = "o template pai é obrigatório.")
    private TemplateRelationShip templateParent;
    /**
     * Construtor da classe <code>TaskPayload</code>.
     * */
    public TaskPayload() {

    }

    /**
     * Esse método verifica se a tarefa está dentro do dia especificado.
     * @return true se a tarefa está dentro do dia, false caso contrário.
     */
    public boolean isTaskWithinDateOfTheDay() {
        if (startTask == null || endTask == null || dateOfTheDay == null) {
            return false;
        }

        LocalDate startTaskDate = startTask.toLocalDate();
        LocalDate endTaskDate = endTask.toLocalDate();

        return !startTaskDate.isBefore(dateOfTheDay) && !endTaskDate.isAfter(dateOfTheDay);
    }
}
